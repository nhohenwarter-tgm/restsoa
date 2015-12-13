import json

def json_to_sql(filein, fileout):
    # Load files
    input = open(filein, 'r')
    output = open(fileout, 'a')

    #load data to list
    data = json.load(input)

    #write data in sql format to file
    for d in data:
        d['bio'] = d['bio'].replace("\n", " ")
        output.write("INSERT INTO search VALUES('"+d['email']+"', '"+d['bio']+"');\n")


json_to_sql('../rawdata/1.json', '../sql/search_insert_1.sql')
json_to_sql('../rawdata/2.json', '../sql/search_insert_2.sql')
json_to_sql('../rawdata/3.json', '../sql/search_insert_3.sql')
json_to_sql('../rawdata/4.json', '../sql/search_insert_4.sql')
json_to_sql('../rawdata/5.json', '../sql/search_insert_5.sql')
json_to_sql('../rawdata/6.json', '../sql/search_insert_6.sql')
json_to_sql('../rawdata/7.json', '../sql/search_insert_7.sql')
json_to_sql('../rawdata/8.json', '../sql/search_insert_8.sql')
json_to_sql('../rawdata/9.json', '../sql/search_insert_9.sql')
json_to_sql('../rawdata/10.json', '../sql/search_insert_10.sql')