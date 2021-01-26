package nl.clemaire.plugins.blua.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import nl.clemaire.plugins.blua.BLuaLanguage
import nl.clemaire.plugins.blua.BLuaFile
import nl.clemaire.plugins.blua.ast.BLuaTypes.*

class BLuaParserDefinition : ParserDefinition {
    private val comments: TokenSet = TokenSet.create(
        LINE_COMMENT, BLOCK_COMMENT)
    val file: IFileElementType = IFileElementType(BLuaLanguage)

    override fun createLexer(p0: Project?): Lexer = BLuaLexerAdapter()
    override fun createParser(p0: Project?): PsiParser = BLuaParser()
    override fun getFileNodeType(): IFileElementType = file
    override fun getCommentTokens(): TokenSet = comments
    override fun getStringLiteralElements(): TokenSet = TokenSet.create(STRING_LITERAL)
    override fun createElement(p0: ASTNode?): PsiElement = Factory.createElement(p0)
    override fun createFile(p0: FileViewProvider): PsiFile = BLuaFile(p0)
}
