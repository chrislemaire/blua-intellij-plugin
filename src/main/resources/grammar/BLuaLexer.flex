package nl.clemaire.plugins.blua.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static nl.clemaire.plugins.blua.ast.BLuaTypes.*;

%%

%{
  public BLuaLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class BLuaLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%x S_BLOCK_COMMENT, S_LINE_COMMENT, S_COMMENT

EOL=\r\n|\r|\n
WHITE_SPACE=[ \t\x0B\f]+

BLOCK_COMMENT_START=\[\[
BLOCK_COMMENT_END=]]\-\-[^\r\n]*
COMMENT_START=\-\-+

OPERATOR=(<=|>=|==|[~]=|not|and|or|\.\.|[+\-*/%<>]|\^)

SYNTAX_SYMBOL=(\.{3}|=>|[;:=,.])
SYNTAX_KEYWORD=(local|return|break|function|if|then|else|elseif|while|for|in|do|repeat|until|end)

C_SQR_OPEN=[\[]
C_SQR_CLOSE=[\]]

C_PAR_OPEN=[(]
C_PAR_CLOSE=[)]

C_MUST_OPEN=[{]
C_MUST_CLOSE=[}]

ID_TOKEN=[^0-9 \"\n\r\t\x0B\f\(\)\[\]\{\}\.\,\:\;]+[^ \n\r\t\x0B\f\(\)\[\]\{\}\.\,\:\;]*

INT=(0|[1-9][0-9]*)
DOUBLE=((0|[1-9][0-9]*)\.[0-9]+)

STRING_LITERAL=\"[^\"\n\r]*\"

%%

<S_BLOCK_COMMENT> {
  {BLOCK_COMMENT_END}   { yybegin(YYINITIAL); return BLOCK_COMMENT; }
  [^]                   { }
  <<EOF>>               { yybegin(YYINITIAL); return BAD_CHARACTER; }
}

<S_LINE_COMMENT> {
  {EOL}                 { yybegin(YYINITIAL); return LINE_COMMENT; }
  [^]                   { }
  <<EOF>>               { yybegin(YYINITIAL); return LINE_COMMENT; }
}

<S_COMMENT> {
  {BLOCK_COMMENT_START} { yybegin(S_BLOCK_COMMENT); }
  [^]                   { yybegin(S_LINE_COMMENT); }
  <<EOF>>               { yybegin(S_LINE_COMMENT); }
}

<YYINITIAL> {
  "true"                { return TRUE; }
  "false"               { return FALSE; }

  {EOL}                 { return EOL; }
  {WHITE_SPACE}         { return WHITE_SPACE; }

  {OPERATOR}            { return OPERATOR; }

  {COMMENT_START}       { yybegin(S_COMMENT); }

  {SYNTAX_SYMBOL}       { return SYNTAX_SYMBOL; }
  {SYNTAX_KEYWORD}      { return SYNTAX_KEYWORD; }

  {C_SQR_OPEN}          { return C_SQR_OPEN; }
  {C_SQR_CLOSE}         { return C_SQR_CLOSE; }

  {C_PAR_OPEN}          { return C_PAR_OPEN; }
  {C_PAR_CLOSE}         { return C_PAR_CLOSE; }

  {C_MUST_OPEN}         { return C_MUST_OPEN; }
  {C_MUST_CLOSE}        { return C_MUST_CLOSE; }

  {INT}                 { return INT; }
  {DOUBLE}              { return DOUBLE; }

  {STRING_LITERAL}      { return STRING_LITERAL; }

  {ID_TOKEN}            { return ID_TOKEN; }

}

[^] { return BAD_CHARACTER; }
