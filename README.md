# wc-command-line-tool
Simple command line tool - wc - cloned using java

## Existing Functionality
1. Works for both relative and absolute paths
2. Validates whether path provided is a file or not
3. Exit command for exiting safely
4. Added support for pipe command mode - Supports any number of pipes but with `wc` as terminal command
5. Supports multiple options at once, both at a time
   and separated by spaces (like `{-cl}` and `{-cl -m}`)
6. Supports multiple files at once
7. Works for file paths with spaces, enclosed in quotes (`{", '}`)

## TODO
1. Multiple char-sets to be supported

## Diagrams
1. Class Diagram
![Class diagram for wc-command-line-tool](resources/wc-command-line-tool-class-diagram.png "Class diagram")

### Test-Run
```commandline
nagi_wc 'resources/some test.txt' "resources/test.txt"
Executing file: resources/some test.txt
Bytes: 21
Words: 10
Lines: 10
Characters: 11
Executing file: resources/test.txt
Bytes: 342190
Words: 58164
Lines: 7145
Characters: 325002
nagi_wc "resources/some test.txt' "resources/test.txt"
Executing file: resources/some test.txt
Bytes: 21
Words: 10
Lines: 10
Characters: 11
Executing file: resources/test.txt
Bytes: 342190
Words: 58164
Lines: 7145
Characters: 325002
nagi_wc -cl -lm 'resources/some test.txt' "resources/test.txt"
Executing file: resources/some test.txt
Bytes: 21
Lines: 10
Characters: 11
Executing file: resources/test.txt
Bytes: 342190
Lines: 7145
Characters: 325002
nagi_wc -clm -lm -w 'resources/some test.txt' "resources/test.txt"
Executing file: resources/some test.txt
Bytes: 21
Words: 10
Lines: 10
Characters: 11
Executing file: resources/test.txt
Bytes: 342190
Words: 58164
Lines: 7145
Characters: 325002
seq 1 10 | sort | uniq | nagi_wc
Executing the command `seq 1 10`...
Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
Executing the command `sort resources/temp.txt`...
Output: [1, 10, 2, 3, 4, 5, 6, 7, 8, 9]
Executing the command `uniq resources/temp.txt`...
Output: [1, 10, 2, 3, 4, 5, 6, 7, 8, 9]
Executing file: resources/temp.txt
Bytes: 21
Words: 10
Lines: 10
Characters: 11
exit
Shutting down...
```