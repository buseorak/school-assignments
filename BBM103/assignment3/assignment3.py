import sys


# This function returns the board to be printed as a string.
def prepare_board(board):
    board_str = "-------------------------\n"
    for i in board:
        for j in i:
            board_str += j + " "
        board_str += "\n"
    board_str += "-------------------------"
    return board_str


# This function finds and returns the coordinates of an entered piece.
def get_position(piece_to_be_found):
    global chessBoard
    for row in chessBoard:
        if piece_to_be_found in row:
            vertical_position = chessBoard.index(row)
            horizontal_position = row.index(piece_to_be_found)
            return [vertical_position, horizontal_position]


# This function returns the piece that occupies the entered coordinates.
def get_content(coordinates_to_convert):
    global chessBoard
    found_piece = chessBoard[coordinates_to_convert[0]][coordinates_to_convert[1]]
    return found_piece


# This function converts the coordinates from list into string to be printed.
def list_to_string(targets_list):
    list_coordinates = [0, 1, 2, 3, 4, 5, 6, 7]
    list_vertical = ["8", "7", "6", "5", "4", "3", "2", "1"]
    list_horizontal = ["a", "b", "c", "d", "e", "f", "g", "h"]
    list_of_pairs = []
    for pair in targets_list:
        pair[0] = list_vertical[list_coordinates.index(pair[0])]
        pair[1] = list_horizontal[list_coordinates.index(pair[1])]
        pair_string = pair[1] + pair[0]
        list_of_pairs.append(pair_string)
    final_string = ""
    for my_pair in sorted(list_of_pairs):
        final_string += my_pair + " "
    return final_string.rstrip()


# This function converts the coordinates from string that has been taken as input into list for computation.
def string_to_list(target_string):
    list_letters = ["a", "b", "c", "d", "e", "f", "g", "h"]
    list_nums = ["8", "7", "6", "5", "4", "3", "2", "1"]
    list_coordinates = [0, 1, 2, 3, 4, 5, 6, 7]
    horizontal_coordinate = list_coordinates[list_letters.index(target_string[0])]
    vertical_coordinate = list_coordinates.index(list_nums.index(target_string[1]))
    coordinates = [vertical_coordinate, horizontal_coordinate]
    return coordinates


# This function eliminates possible wrong results with nonsense coordinates.
def check_range(the_targets):
    my_final_targets = []
    for c in the_targets:
        if c[0] < 0 or c[1] < 0 or c[0] > 7 or c[1] > 7:
            pass
        else:
            my_final_targets.append(c)
    return my_final_targets


# The following eleven functions return the possible targets to move, without considering specific situations.

def movement_king(v, h):
    targets = [[v-1, h-1], [v-1, h], [v-1, h+1], [v, h-1], [v, h+1], [v+1, h-1], [v+1, h], [v+1, h+1]]
    return check_range(targets)


def movement_pawn(v, h, c):
    if c == "white":
        target = [[v-1, h]]
    else:
        target = [[v+1, h]]
    return check_range(target)


def movement_knight_l(v, h):
    targets = [[v-2, h-1], [v-2, h+1], [v-1, h-2], [v-1, h+2], [v+1, h-2], [v+1, h+2], [v+2, h-1], [v+2, h+1]]
    return check_range(targets)


def movement_knight_d(v, h):
    targets = [[v-1, h-1], [v-1, h+1], [v+1, h-1], [v+1, h+1]]
    return check_range(targets)


def movement_rook_right(v, h):
    targets = [[v, h+a] for a in range(1, 8-h)]
    return check_range(targets)


def movement_rook_left(v, h):
    targets = [[v, h-a] for a in range(1, h+1)]
    return check_range(targets)


def movement_rook_down(v, h):
    targets = [[v+a, h] for a in range(1, 8-v)]
    return check_range(targets)


def movement_rook_up(v, h):
    targets = [[v-a, h] for a in range(1, v+1)]
    return check_range(targets)


def movement_bishop_right(v, h, c):
    targets = []
    for a in range(1, 8):
        if c == "white":
            targets.append([v-a, h+a])
        else:
            targets.append([v+a, h+a])
    return check_range(targets)


def movement_bishop_left(v, h, c):
    targets = []
    for a in range(1, 8):
        if c == "white":
            targets.append([v-a, h-a])
        else:
            targets.append([v+a, h-a])
    return check_range(targets)


def movement_queen(v, h):
    targets = []
    for t in movement_rook_right(v, h):
        targets.append(t)
    for t in movement_rook_left(v, h):
        targets.append(t)
    for t in movement_rook_down(v, h):
        targets.append(t)
    for t in movement_rook_up(v, h):
        targets.append(t)
    for t in movement_bishop_right(v, h, "white"):
        targets.append(t)
    for t in movement_bishop_left(v, h, "white"):
        targets.append(t)
    for t in movement_bishop_right(v, h, "black"):
        targets.append(t)
    for t in movement_bishop_left(v, h, "black"):
        targets.append(t)
    return check_range(targets)


# This function does the similar specific computations needed for king, pawn, and knight ("L" motion only), returns final targets.
def lift_drop_pieces(piece_1):
    global chessBoard
    vertical = get_position(piece_1)[0]
    horizontal = get_position(piece_1)[1]
    targets_1 = []
    if piece_1.upper() == "KI":
        targets_1 = movement_king(vertical, horizontal)
    elif piece_1[0] == "P":
        targets_1 = movement_pawn(vertical, horizontal, "black")
    elif piece_1[0] == "p":
        targets_1 = movement_pawn(vertical, horizontal, "white")
    elif piece_1[0].upper() == "N":
        targets_1 = movement_knight_l(vertical, horizontal)

    final_list_ld = []
    for target_1 in targets_1:
        target_content = get_content(target_1)
        if target_content == "  ":
            final_list_ld.append(target_1)
        else:
            if (piece_1 in black_pieces and target_content in white_pieces) or (piece_1 in white_pieces and target_content in black_pieces):
                if target_content.upper() != "KI":
                    final_list_ld.append(target_1)
    return final_list_ld


# This function does the similar specific computations needed for rook, bishop, and queen, returns final targets.
def drag_pieces(piece_2):
    global chessBoard
    vertical = get_position(piece_2)[0]
    horizontal = get_position(piece_2)[1]
    targets_2 = []
    if piece_2[0].upper() == "R":
        targets_2.append(movement_rook_right(vertical, horizontal))
        targets_2.append(movement_rook_left(vertical, horizontal))
        targets_2.append(movement_rook_down(vertical, horizontal))
        targets_2.append(movement_rook_up(vertical, horizontal))
    elif piece_2[0] == "B":
        targets_2.append(movement_bishop_right(vertical, horizontal, "black"))
        targets_2.append(movement_bishop_left(vertical, horizontal, "black"))
    elif piece_2[0] == "b":
        targets_2.append(movement_bishop_right(vertical, horizontal, "white"))
        targets_2.append(movement_bishop_left(vertical, horizontal, "white"))
    elif piece_2.upper() == "QU":
        targets_2.append(movement_rook_right(vertical, horizontal))
        targets_2.append(movement_rook_left(vertical, horizontal))
        targets_2.append(movement_rook_down(vertical, horizontal))
        targets_2.append(movement_rook_up(vertical, horizontal))
        targets_2.append(movement_bishop_right(vertical, horizontal, "black"))
        targets_2.append(movement_bishop_left(vertical, horizontal, "black"))
        targets_2.append(movement_bishop_right(vertical, horizontal, "white"))
        targets_2.append(movement_bishop_left(vertical, horizontal, "white"))

    final_list_d = []
    for group in targets_2:
        for target_2 in group:
            target_content = get_content(target_2)
            if target_content == "  ":
                final_list_d.append(target_2)
            else:
                if (piece_2 in black_pieces and target_content in black_pieces) or (piece_2 in white_pieces and target_content in white_pieces):
                    break
                elif (piece_2 in black_pieces and target_content in white_pieces) or (piece_2 in white_pieces and target_content in black_pieces):
                    if target_content.upper() != "KI":
                        final_list_d.append(target_2)
                        break
    return final_list_d


# This function does specific computations needed for knight ("diagonal" motion only).
def special_knight_diagonal(knight_diagonal):
    global chessBoard
    vertical = get_position(knight_diagonal)[0]
    horizontal = get_position(knight_diagonal)[1]
    final_list_k = []
    for target_d in movement_knight_d(vertical, horizontal):
        target_content = get_content(target_d)
        if target_content == "  ":
            final_list_k.append(target_d)
    return final_list_k


f = open(sys.argv[1], "r")
commands = [[line.split()] for line in f.readlines()]
f.close()

black_pieces = ["R1", "N1", "B1", "QU", "KI", "B2", "N2", "R2", "P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8"]
white_pieces = ["r1", "n1", "b1", "qu", "ki", "b2", "n2", "r2", "p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"]

chessBoardInitial = [["R1", "N1", "B1", "QU", "KI", "B2", "N2", "R2"], ["P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8"],
                     ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "], ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
                     ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "], ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
                     ["p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"], ["r1", "n1", "b1", "qu", "ki", "b2", "n2", "r2"]]

chessBoard = [["R1", "N1", "B1", "QU", "KI", "B2", "N2", "R2"], ["P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8"],
              ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "], ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
              ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "], ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
              ["p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"], ["r1", "n1", "b1", "qu", "ki", "b2", "n2", "r2"]]


for num in range(len(commands)):
    if commands[num][0][0] == 'initialize':
        print("> initialize")
        print(prepare_board(chessBoardInitial))
        chessBoard = [["R1", "N1", "B1", "QU", "KI", "B2", "N2", "R2"], ["P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8"],
                      ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "], ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
                      ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "], ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
                      ["p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"], ["r1", "n1", "b1", "qu", "ki", "b2", "n2", "r2"]]
    elif commands[num][0][0] == 'print':
        print("> print")
        print(prepare_board(chessBoard))
    elif commands[num][0][0] == 'exit':
        print("> exit")
        exit()

    elif commands[num][0][0] == 'showmoves':
        moving_piece = commands[num][0][1]
        print("> showmoves " + moving_piece)
        print_this = ""
        if moving_piece.upper() == "KI":
            print_this = list_to_string(lift_drop_pieces(moving_piece))
        elif moving_piece[0].upper() == "P":
            print_this = list_to_string(lift_drop_pieces(moving_piece))
        elif moving_piece[0].upper() == "N":
            knight_targets = []
            for knight_target in lift_drop_pieces(moving_piece):
                knight_targets.append(knight_target)
            for knight_target in special_knight_diagonal(moving_piece):
                knight_targets.append(knight_target)
            print_this = list_to_string(knight_targets)
        elif moving_piece[0].upper() == "R":
            print_this = list_to_string(drag_pieces(moving_piece))
        elif moving_piece[0].upper() == "B":
            print_this = list_to_string(drag_pieces(moving_piece))
        elif moving_piece.upper() == "QU":
            print_this = list_to_string(drag_pieces(moving_piece))
        if len(print_this) == 0:
            print("FAILED")
        else:
            print(print_this)

    elif commands[num][0][0] == 'move':
        moving_piece = commands[num][0][1]
        print("> move " + moving_piece + " " + commands[num][0][2])
        current_coordinates = get_position(moving_piece)
        target_coordinates = string_to_list(commands[num][0][2])

        # This function moves the pieces and prints "OK" for "move" function.
        def changer(target_coord, current_coord, mov_piece):
            global chessBoard
            print("OK")
            chessBoard[target_coord[0]][target_coord[1]] = mov_piece
            chessBoard[current_coord[0]][current_coord[1]] = "  "

        if (moving_piece.upper() == "KI") and (target_coordinates in lift_drop_pieces(moving_piece)):
            changer(target_coordinates, current_coordinates, moving_piece)
        elif (moving_piece[0].upper() == "P") and (target_coordinates in lift_drop_pieces(moving_piece)):
            changer(target_coordinates, current_coordinates, moving_piece)
        elif (moving_piece[0].upper() == "N") and (target_coordinates in lift_drop_pieces(moving_piece)):
            changer(target_coordinates, current_coordinates, moving_piece)
        elif (moving_piece[0].upper() == "N") and (target_coordinates in special_knight_diagonal(moving_piece)):
            changer(target_coordinates, current_coordinates, moving_piece)
        elif (moving_piece[0].upper() == "R") and (target_coordinates in drag_pieces(moving_piece)):
            changer(target_coordinates, current_coordinates, moving_piece)
        elif (moving_piece[0].upper() == "B") and (target_coordinates in drag_pieces(moving_piece)):
            changer(target_coordinates, current_coordinates, moving_piece)
        elif (moving_piece.upper() == "QU") and (target_coordinates in drag_pieces(moving_piece)):
            changer(target_coordinates, current_coordinates, moving_piece)
        else:
            print("FAILED")
