package nl.clemaire.plugins.blua.ast.mixin.expr

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaFunctionExpr
import nl.clemaire.plugins.blua.ast.getPreviousScope
import nl.clemaire.plugins.blua.ast.impl.BLuaExprImpl

abstract class BLuaFunctionExprMixin(node: ASTNode) : BLuaExprImpl(node), BLuaFunctionExpr {
    override fun scope(): Map<String, PsiElement> {
        val declared = (params?.scope() ?: mapOf())

        // Declared parameters and function name override previous scope names
        return getPreviousScope() + declared
    }
}
