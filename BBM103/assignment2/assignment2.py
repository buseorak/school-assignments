# This function returns False if there is an empty row, True if there is not.
def check_if_empty(x):
    for j in x:
        if not j:
            return False
        else:
            return True


# This function returns False if a row or column is longer than 10, True if else.
def check_length(x):
    for j in x:
        if len(j) > 10 or len(x) > 10:
            return False
        else:
            return True


# This function returns False if the map is not completed as a rectangle/square, True if it is completed.
def check_if_rectangle(x):
    horizontal_size = len(x[0])
    for j in x:
        if len(j) != horizontal_size:
            return False
    return True


# This function returns map of the game as a string.
def get_my_map():
    global rowsList
    feeding_map = ""
    for a in rowsList:
        for b in a:
            feeding_map += b + " "
        feeding_map += "\n"
    return feeding_map


# This function finds the current position of the rabbit.
def get_position():
    global horizontalPosition
    global verticalPosition
    counter = 0
    for a in rowsList:
        if "*" not in a:
            counter += 1
        elif "*" in a:
            verticalPosition = counter
            horizontalPosition = rowsList[verticalPosition].index("*")
            break


# This function returns False if there is a wall, True if the rabbit can move and changes the score accordingly.
# Also, this function determines the value of go_to_next_direction, False indicating that the game is over.
def check_if_can_move(y, z):
    global go_to_next_direction
    global score
    global rowsList
    if rowsList[z][y] == "P":
        go_to_next_direction = False
    else:
        go_to_next_direction = True
    if rowsList[z][y] == "W":
        return False
    else:
        if rowsList[z][y] == "C":
            score += 10
        elif rowsList[z][y] == "A":
            score += 5
        elif rowsList[z][y] == "M":
            score -= 5
        elif rowsList[z][y] == "P" or rowsList[z][y] == "X":
            pass
        return True


# This function does the replacement horizontally and returns True if the rabbit is not getting out of the board.
def move_horizontally(x):
    global rowsList
    get_position()
    if x == "R":
        target = horizontalPosition + 1
        if target >= horizontalSize:
            return False
    else:
        target = horizontalPosition - 1
        if target < 0:
            return False
    movement = check_if_can_move(target, verticalPosition)
    if movement:
        rowsList[verticalPosition][target] = "*"
        rowsList[verticalPosition][horizontalPosition] = "X"
        return True
    else:
        return False


# This function does the replacement vertically and returns True if the rabbit is not getting out of the board.
def move_vertically(x):
    global rowsList
    get_position()
    if x == "D":
        target = verticalPosition + 1
        if target >= verticalSize:
            return True
    else:
        target = verticalPosition - 1
        if target < 0:
            return True
    movement = check_if_can_move(horizontalPosition, target)
    if movement:
        rowsList[target][horizontalPosition] = "*"
        rowsList[verticalPosition][horizontalPosition] = "X"
        return True
    else:
        return False


rowsList = []
while (not check_if_empty(rowsList)) or (not check_length(rowsList)) or (not check_if_rectangle(rowsList)):
    map0 = input("Please enter feeding map as a list:\n")
    while map0.count("\'*\'") != 1:
        map0 = input("Please enter feeding map as a list:\n")
    rowsList.clear()
    list0 = map0.split("], [")
    for i in list0:
        i = i.replace("[", "").replace("]", "").replace("\'", "").replace(",", "")
        row = i.split()
        rowsList.append(row)

verticalSize = len(rowsList)
horizontalSize = len(rowsList[0])

myDirections = []
while len(myDirections) > 15 or len(myDirections) == 0:
    directions0 = input("Please enter direction of movements as a list:\n")
    directions0 = directions0.replace("[", "").replace("]", "").replace("\'", "").replace(" ", "")
    myDirections = directions0.split(",")

print("Your board is:\n" + get_my_map(), end='')

horizontalPosition = 0
verticalPosition = 0
go_to_next_direction = True
score = 0


for i in myDirections:
    if i == "R" or i == "L":
        f = move_horizontally(i)
    else:
        f = move_vertically(i)
    if not f:
        continue
    elif f:
        if go_to_next_direction:
            continue
        else:
            break

print("Your output should be like this:\n" + get_my_map() + "Your score is: " + str(score))
