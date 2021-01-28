package nl.clemaire.plugins.blua.ast.mixin.stmt

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaAssignStmt
import nl.clemaire.plugins.blua.ast.BLuaVarExpr
import nl.clemaire.plugins.blua.ast.getPreviousScope
import nl.clemaire.plugins.blua.ast.impl.BLuaStatementImpl

abstract class BLuaAssignStmtMixin(node: ASTNode) : BLuaStatementImpl(node), BLuaAssignStmt {
    override fun scope(): Map<String, PsiElement> {
        val declared = vars.exprList
            .mapNotNull { (it as BLuaVarExpr).typedId?.idToken }
            .map { it.text to it }
            .toMap()

        // Previously defined names override assignment declarations
        return declared + getPreviousScope()
    }
}
