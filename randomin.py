import random

num_files = 50

min_nodes = 50 
max_nodes = 50

min_weight = 0
max_weight = 100

zero_diags = True

for file_num in range(num_files):
    file_name = 'random' + str(file_num) + '.in'
    num_nodes = None
    while not num_nodes or num_nodes % 2 != 0:
        num_nodes = random.randint(min_nodes, max_nodes)
    
    matrix = [[0 for x in range(num_nodes)] for y in range(num_nodes)] 
    for j in range(num_nodes):
        for k in range(int(j), num_nodes):
            weight = str(random.randint(min_weight, max_weight))
            if j == k and zero_diags:
                weight = str(0)
            matrix[j][k] = weight

    for j in reversed(range(num_nodes)):
        for k in range(0, j):
            matrix[j][k] = matrix[k][j]



    with open(file_name, 'w') as f:
        f.write(str(num_nodes) + '\n')
        
        for row in matrix:
            for num in row:
                f.write(str(num) + " ")

            f.write('\n')

        city_string = 'R' * (num_nodes / 2) + ('B' * (num_nodes/2))
        city_string = ''.join(random.sample(city_string, len(city_string)))
        f.write(city_string)



