#!/usr/bin/python
import json
import sys
import os
import CppHeaderParser
from queue import Queue

def ParseFile( file_name):
    print ("start parse " + file_name)
    output_json = {}
    output_json["file_path"] = file_name
    output_json["file_name"] = os.path.basename(file_name)
    try:
        cppHeader = CppHeaderParser.CppHeader(file_name)
    except (CppHeaderParser.CppParseError, BaseException) as e:
        print("\033[1;31;40mParse " + file_name + " with error:" + e.__str__() + "\033[0m")
        output_json["error"] = "Parse error"
        return output_json

    # print("CppHeaderParser view of %s"%cppHeader)

    # MARCO
    marcos = {}
    for define in cppHeader.defines:
        # print (define)
        pair = define.split(" ")
        key = pair[0]
        i=1
        while(i < len(pair) and pair[i] == "") :
            i = i +1
        if i < len(pair):
            value = pair[i]
            # print(key + " = " + value)
            marcos[key] = value
    output_json["macros"] = marcos

    # Struct
    structs = []
    for object in cppHeader.classes:
        struct = cppHeader.classes[object.__str__()]
        struct_json = {}
        struct_json["name"] = struct["name"]
        # print("name is " + struct["name"])
        struct_json["props"] = []
        properties = struct["properties"]["public"]
        for prop in properties:
            if (prop["static"] != 0) :
                continue
            # print("prop is " + prop["type"] + " " + prop["name"])
            # print("prop is " + prop.__str__())
            prop_json = {"type":prop["type"], "name":prop["name"]}
            struct_json["props"].append(prop_json)
            if prop.__contains__("array_size") :
                prop_json["array_size"] = prop["array_size"]
        # print("struct json:\n" + json.dumps(struct_json, indent=4))
        structs.append(struct_json)
    output_json["structs"] = structs

    # print(json.dumps(output_json, indent=4))

    return output_json

print (sys.argv)

file_name = "dl_rlc_ul_rlc_interface.h"

if len(sys.argv) > 1:
    file_name = sys.argv[1]

result = []

file_queue = Queue()
file_queue.put(file_name)
while not file_queue.empty():
    cur_file = file_queue.get()
    if os.path.isdir(cur_file):
        list = os.listdir(cur_file)
        for item in list:
            item_path = os.path.join(cur_file, item)
            file_queue.put(item_path)
    elif os.path.isfile(cur_file) and cur_file.endswith(".h"):
        result.append(ParseFile(cur_file))

output = open("output.json", "w+")
output.write(json.dumps(result, indent=4))
output.close()
# print (json.dumps(result, indent=4))
# print (result)