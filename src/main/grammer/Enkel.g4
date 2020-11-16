//header
grammar Enkel;

//parser rules
compilationUnit : classDeclaration EOF ; //root rule - our code consist consist only of variables and prints (see definition below)
classDeclaration : className '{' classBody '}' ;
className : ID ;
classBody :  ( variable | print )* ;
variable : VARIABLE ID EQUALS value;
print : PRINT ID ; //print statement must consist of 'print' keyword and ID
value : NUMBER
      | STRING ; //must be NUMBER or STRING value (defined below)

//lexer rules (tokens)
VARIABLE : 'var' ; //VARIABLE TOKEN must match exactly 'var'
PRINT : 'print' ;
EQUALS : '=' ; //must be '='
NUMBER : [0-9]+ ; //must consist only of digits
STRING : '"'.*'"' ; //must be anything in qutoes
ID : [a-zA-Z0-9]+ ; //must be any alphanumeric value
WS: [ \t\n\r]+ -> skip ; //special TOKEN for skipping whitespaces