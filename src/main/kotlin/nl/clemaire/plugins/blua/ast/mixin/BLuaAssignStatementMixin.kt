package nl.clemaire.plugins.blua.ast.mixin

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaAssignStmt
import nl.clemaire.plugins.blua.ast.BLuaVarRef
import nl.clemaire.plugins.blua.ast.ext.BLuaDeclaration
import nl.clemaire.plugins.blua.ast.findPreviousDeclarationNode
import nl.clemaire.plugins.blua.ast.impl.BLuaStatementImpl
import nl.clemaire.plugins.blua.ast.previousScope

abstract class BLuaAssignStatementMixin(node: ASTNode) : BLuaStatementImpl(node), BLuaAssignStmt,
    BLuaDeclaration {
    override fun scope(): Map<String, PsiElement> {
        val declared = vars.exprList
            .filter { it is BLuaVarRef }
            .map { (it as BLuaVarRef).text to it }
            .toMap()

        // Previously defined names override assignment declarations
        return declared + previousScope()
    }
}
