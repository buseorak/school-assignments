import sys
import os


# This function checks whether the file is readable or not.
def check_file_readability(fi):
    fi_rev = fi[::-1]
    if "/" in fi_rev:
        fi_name = fi_rev[fi_rev.find(".") + 1:fi_rev.find("/")]
    elif "\\" in fi_rev:
        fi_name = fi_rev[fi_rev.find(".") + 1:fi_rev.find("\\")]
    fi_extension = fi_rev[:fi_rev.find(".") + 1]
    dir_name = os.path.dirname(fi)
    for fil in os.listdir(dir_name):
        fil_rev = fil[::-1]
        fil_name = fil_rev[fil_rev.find(".") + 1:]
        fil_extension = fil_rev[:fil_rev.find(".") + 1]
        if fi_name == fil_name and fi_extension != fil_extension:
            return True
    return False


# This function returns the input as a list if no error raises.
def input_to_list(fi, operation):
    try:
        with open(fi, "r") as g:
            input_file_empty = True
            for a_line in g:
                if operation == "enc":
                    input_list = [letter for letter in a_line.strip("\n").upper()]
                else:
                    input_list = [number for number in a_line.replace("\n", "").split(",")]
                input_file_empty = False
        if input_file_empty:
            print("Input file is empty error")
            exit()
    except FileNotFoundError:
        if check_file_readability(sys.argv[3]):
            print("The input file could not be read error")
            exit()
        else:
            print("Input file not found error")
            exit()
    else:
        if operation == "enc":
            for char in input_list:
                if char not in letter_to_number.keys():
                    print("Invalid character in input file error")
                    exit()
        else:
            try:
                for n in range(len(input_list)):
                    input_list[n] = int(input_list[n])
            except ValueError:
                print("Invalid character in input file error")
                exit()
    return input_list


# This function does the conversion of numbers and letters with respect to the related dict.
def converter(a_list, a_dict):
    for indexNum in range(len(a_list)):
        a_list[indexNum] = a_dict[a_list[indexNum]]


# This function returns the resulting multiplied matrix.
def matrix_multiplier(a_list, a_key):
    multiplied_matrices = []
    for sub_matrix in a_list:
        resultant_matrix = []
        for a in range(key_length):
            row = 0
            for b in range(key_length):
                row += a_key[a][b] * sub_matrix[b]
            resultant_matrix.append(row)
        multiplied_matrices.append(resultant_matrix)
    return multiplied_matrices


# This function returns matrices grouped with respect to the key length.
def prepare_sub_matrix(a_list):
    sub_matrices = []
    for x in range(len(a_list) // key_length):
        sub_matrices.append(a_list[key_length*x:key_length*(x+1)])
    return sub_matrices


# This function returns the sub-matrices needed for computing the determinant.
def prepare_mini_matrix(matrix, horizontal, vertical):
    matrix = [m[:] for m in matrix]
    matrix.pop(horizontal)
    for r in matrix:
        r.pop(vertical)
    return matrix


# This function computes the determinant.
def determinant(matrix):
    result = 0
    if len(matrix) == 2:
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
    else:
        entry_index = -1
        for e in matrix[0]:
            entry_index += 1
            result += (-1)**entry_index * e * determinant(prepare_mini_matrix(matrix, 0, entry_index))
    return result


# This function computes the determinant for cofactor expansion.
def cofactor_calculator(matrix):
    result = 0
    if len(matrix) == 2:
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
    else:
        row_index = -1
        for r in matrix:
            row_index += 1
            entry_index = -1
            for e in r:
                entry_index += 1
                result += (-1)**entry_index * e * determinant(prepare_mini_matrix(matrix, row_index, entry_index))
    return result


if len(sys.argv) != 5:
    print("Parameter number error")
    exit()
if sys.argv[1] != "enc":
    if sys.argv[1] != "dec":
        print("Undefined parameter error")
        exit()


letter_to_number = {"A": 1, "B": 2, "C": 3, "D": 4, "E": 5, "F": 6, "G": 7, "H": 8, "I": 9, "J": 10,
                    "K": 11, "L": 12, "M": 13, "N": 14, "O": 15, "P": 16, "Q": 17, "R": 18, "S": 19,
                    "T": 20, "U": 21, "V": 22, "W": 23, "X": 24, "Y": 25, "Z": 26, " ": 27}
number_to_letter = {1: "A", 2: "B", 3: "C", 4: "D", 5: "E", 6: "F", 7: "G", 8: "H", 9: "I", 10: "J",
                    11: "K", 12: "L", 13: "M", 14: "N", 15: "O", 16: "P", 17: "Q", 18: "R", 19: "S",
                    20: "T", 21: "U", 22: "V", 23: "W", 24: "X", 25: "Y", 26: "Z", 27: " "}

key = []
try:
    with open(sys.argv[2], "r") as f:
        keyFileEmpty = True
        for line in f:
            try:
                for i in line.replace("\n", "").split(","):
                    if " " in i:
                        print("Invalid character in key file error")
                        exit()
                key.append([int(i) for i in line.replace("\n", "").split(",")])
                keyFileEmpty = False
            except ValueError:
                print("Invalid character in key file error")
                exit()
    if keyFileEmpty:
        print("Key file is empty error")
        exit()
except FileNotFoundError:
    if check_file_readability(sys.argv[2]):
        print("Key file could not be read error")
        exit()
    else:
        print("Key file not found error")
        exit()
finally:
    key_length = len(key)

operationType = sys.argv[1]

if operationType == "enc":
    input_enc = input_to_list(sys.argv[3], "enc")
    while len(input_enc) % key_length != 0:
        input_enc.append(" ")

    converter(input_enc, letter_to_number)
    sub_matrices_enc = prepare_sub_matrix(input_enc)
    multiplied_matrices_enc = matrix_multiplier(sub_matrices_enc, key)

    encrypted_text = ""
    for i in multiplied_matrices_enc:
        for j in i:
            encrypted_text += str(j) + ","

    dir_path = os.path.dirname(sys.argv[0])
    output_path = os.path.join(dir_path, sys.argv[4])
    output_enc = open(output_path, "w")
    output_enc.write(encrypted_text.rstrip(","))
    output_enc.close()

elif operationType == "dec":
    input_dec = input_to_list(sys.argv[3], "dec")

    sub_matrices_dec = prepare_sub_matrix(input_dec)

    key_determinant = determinant(key)
    if len(key) > 2:
        matrix_of_minors = []
        i_index = -1
        for i in key:
            i_index += 1
            j_index = -1
            matrix_of_minors_j = []
            for j in i:
                j_index += 1
                matrix_of_minors_j.append(cofactor_calculator(prepare_mini_matrix(key, i_index, j_index)))
            matrix_of_minors.append(matrix_of_minors_j)

        for i in range(len(matrix_of_minors)):
            for j in range(len(matrix_of_minors)):
                if (i + j) % 2 == 1:
                    matrix_of_minors[i][j] = -matrix_of_minors[i][j]

        adjugate = [i[:] for i in matrix_of_minors]
        for i in range(len(matrix_of_minors)):
            for j in range(len(matrix_of_minors)):
                adjugate[j][i] = matrix_of_minors[i][j]
    else:
        adjugate = [[key[1][1], -key[0][1]], [-key[1][0], key[0][0]]]

    inverse_key = []
    for i in adjugate:
        inverse_row = []
        for j in i:
            inverse_row.append(j * (1 / key_determinant))
        inverse_key.append(inverse_row)

    multiplied_matrices_dec = matrix_multiplier(sub_matrices_dec, inverse_key)
    decrypted_list = []
    for i in multiplied_matrices_dec:
        for j in i:
            decrypted_list.append(j)

    converter(decrypted_list, number_to_letter)
    plain_text = ""
    for i in decrypted_list:
        plain_text += str(i)

    dir_path = os.path.dirname(sys.argv[0])
    output_path = os.path.join(dir_path, sys.argv[4])
    output_dec = open(output_path, "w")
    output_dec.write(plain_text.rstrip())
    output_dec.close()
