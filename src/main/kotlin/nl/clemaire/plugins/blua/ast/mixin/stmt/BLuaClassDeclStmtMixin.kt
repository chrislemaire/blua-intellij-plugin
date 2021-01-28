package nl.clemaire.plugins.blua.ast.mixin.stmt

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaClassDeclStmt
import nl.clemaire.plugins.blua.ast.BLuaElementFactory
import nl.clemaire.plugins.blua.ast.impl.BLuaStatementImpl

abstract class BLuaClassDeclStmtMixin(node: ASTNode) : BLuaStatementImpl(node), BLuaClassDeclStmt {
    override fun getName(): String? = idToken.text

    override fun setName(name: String): PsiElement {
        idToken.replace(BLuaElementFactory.createIdToken(project, name))
        return this
    }

    override fun getNameIdentifier(): PsiElement? = idToken

    override fun getTextOffset(): Int =
        idToken.textOffset
}
