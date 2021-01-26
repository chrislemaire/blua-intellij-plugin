package nl.clemaire.plugins.blua.ast.ext

import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaVarExpr
import nl.clemaire.plugins.blua.ast.BLuaVars
import nl.clemaire.plugins.blua.ast.getPreviousScope

interface BLuaAssignStmtExt : Scoped {
    /**
     * Gets the BLuaVars element under this Assign node.
     */
    fun getVars(): BLuaVars

    @JvmDefault
    override fun scope(): Map<String, PsiElement> {
        val declared = getVars().exprList
            .mapNotNull { (it as BLuaVarExpr).idToken }
            .map { it.text to it }
            .toMap()

        // Previously defined names override assignment declarations
        return declared + getPreviousScope()
    }
}
