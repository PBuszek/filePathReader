# Assignment: FilePartReader testing with JUnit

In this assignment, we will work with files, because we can't have enough file
readers. :) Your job is to implement 2 classes and cover them with tests.

## Implementation

### FilePartReader class

#### It has one **constructor** :

- it sets the class' instance variables to some invalid default value

#### It has three instance methods:

- public void **setup(String filePath, Integer fromLine, Integer toLine)**
  - it throws an _IllegalArgumentException_ :
    - if _toLine_ is smaller than _fromLine_
    - if _fromLine_ is smaller than 1
    
- public String **read()**
  - opens the file on _filePath_ , and gives back it's content as a String
  - it doesn't catch the exception being raised, if the file isn't present on
    _filePath_, we can expect an `IOException`
    
- public String **readLines()**:
  - reads the file with **read()**
  - it gives back every line from its content between _fromLine_ and _toLine_
    (both of them are included), and returns these lines as a String. Take care
    because if _fromLine_ is 1, it means the very first row in the file. Also,
    if _fromLine_ is 1 and _toLine_ is 1 also, we will read only the very first
    line.

### FileWordAnalyzer class

#### It has one **constructor** :

- its parameter is a **FilePartReader** object

#### It has three instance methods:

- public List **getWordsOrderedAlphabetically()**:
  - calls **FilePartReader.readLines()**
  - returns the words ordered by alphabetically as an ArrayList
  
- public List **getWordsContainingSubstring(String _subString_)**:
  - calls **FilePartReader.readLines()**
  - returns the words which contains the _subString_
  
- public List **getStringsWhichPalindromes()**:
  - calls **FilePartReader.readLines()**
  - returns the words from the String which are palindrome

## Testing

When you are ready, your job is to cover your code with tests, and make an
assertion for all the statements in the bullet points. When testing
**FilePartReader** class, you can have a test file with which you can test the
read method.