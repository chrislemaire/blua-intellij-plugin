package nl.clemaire.plugins.blua.ast.mixin.stmt

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaElementFactory
import nl.clemaire.plugins.blua.ast.BLuaNamedFunctionStmt
import nl.clemaire.plugins.blua.ast.getPreviousScope
import nl.clemaire.plugins.blua.ast.impl.BLuaStatementImpl

abstract class BLuaNamedFunctionStmtMixin(node: ASTNode) : BLuaStatementImpl(node), BLuaNamedFunctionStmt {
    override fun getName(): String? = idToken?.text

    override fun setName(name: String): PsiElement {
        idToken?.replace(BLuaElementFactory.createIdToken(project, name))
        return this
    }

    override fun getNameIdentifier(): PsiElement? = idToken

    override fun getTextOffset(): Int =
        idToken?.textOffset ?: super.getTextOffset()

    override fun scope(): Map<String, PsiElement> {
        val declared = (idToken?.let { mapOf(it.text to it) } ?: mapOf()) +
                (params?.scope() ?: mapOf())

        // Declared parameters and function name override previous scope names
        return getPreviousScope() + declared
    }
}
