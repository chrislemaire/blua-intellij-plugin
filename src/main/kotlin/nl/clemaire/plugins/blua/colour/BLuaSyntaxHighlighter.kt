package nl.clemaire.plugins.blua.colour

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import nl.clemaire.plugins.blua.ast.BLuaTypes.*
import nl.clemaire.plugins.blua.parser.BLuaLexerAdapter

class BLuaSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        val separator: TextAttributesKey =
            createTextAttributesKey("SIMPLE_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val key: TextAttributesKey =
            createTextAttributesKey("SIMPLE_KEY", DefaultLanguageHighlighterColors.KEYWORD)
        val value: TextAttributesKey =
            createTextAttributesKey("SIMPLE_VALUE", DefaultLanguageHighlighterColors.STRING)
        val comment: TextAttributesKey =
            createTextAttributesKey("SIMPLE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val badCharacter: TextAttributesKey =
            createTextAttributesKey("SIMPLE_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
    }

    override fun getHighlightingLexer(): Lexer = BLuaLexerAdapter()
    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> = when (tokenType) {
        LINE_COMMENT -> arrayOf(DefaultLanguageHighlighterColors.LINE_COMMENT)
        BLOCK_COMMENT -> arrayOf(DefaultLanguageHighlighterColors.BLOCK_COMMENT)

        C_MUST_CLOSE -> arrayOf(DefaultLanguageHighlighterColors.BRACES)
        C_MUST_OPEN -> arrayOf(DefaultLanguageHighlighterColors.BRACES)
        C_PAR_CLOSE -> arrayOf(DefaultLanguageHighlighterColors.PARENTHESES)
        C_PAR_OPEN -> arrayOf(DefaultLanguageHighlighterColors.PARENTHESES)
        C_SQR_CLOSE -> arrayOf(DefaultLanguageHighlighterColors.BRACKETS)
        C_SQR_OPEN -> arrayOf(DefaultLanguageHighlighterColors.BRACKETS)

        ID -> arrayOf(DefaultLanguageHighlighterColors.IDENTIFIER)

        INT -> arrayOf(DefaultLanguageHighlighterColors.NUMBER)
        DOUBLE -> arrayOf(DefaultLanguageHighlighterColors.NUMBER)

        STRING_LITERAL -> arrayOf(DefaultLanguageHighlighterColors.STRING)

        OPERATOR -> arrayOf(DefaultLanguageHighlighterColors.OPERATION_SIGN)

        SYNTAX_KEYWORD -> arrayOf(DefaultLanguageHighlighterColors.KEYWORD)
        SYNTAX_SYMBOL -> arrayOf(DefaultLanguageHighlighterColors.OPERATION_SIGN)

        TRUE -> arrayOf(DefaultLanguageHighlighterColors.KEYWORD)
        FALSE -> arrayOf(DefaultLanguageHighlighterColors.KEYWORD)

        TokenType.BAD_CHARACTER -> arrayOf(HighlighterColors.BAD_CHARACTER)

        else -> arrayOf()
    }
}
