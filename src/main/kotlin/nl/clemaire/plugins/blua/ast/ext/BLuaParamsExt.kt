package nl.clemaire.plugins.blua.ast.ext

import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaTypedIds

interface BLuaParamsExt : PsiElement {
    /**
     * Gets the BLuaIds element under this Params node.
     */
    fun getTypedIds(): BLuaTypedIds?

    @JvmDefault
    fun scope(): Map<String, PsiElement> =
        getTypedIds()?.typedIdList?.map { it.idToken.text to it.idToken }?.toMap() ?: mapOf()
}
