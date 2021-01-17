@file:JvmName("BLuaUtil")

package nl.clemaire.plugins.blua.ast

import com.github.izhangzhihao.rainbow.brackets.util.findPrevSibling
import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.ext.BLuaDeclaration

fun PsiElement.lookupDeclaration(name: String): PsiElement? {
    if (this is BLuaDeclaration) {
        val decl = scope()[name]
        if (decl != null)
            return decl
    }

    return (findPrevSibling { it is BLuaDeclaration } ?: parent)?.lookupDeclaration(name)
}

/**
 * Finds a previous or upper previous sibling or parent that satisfies the given boolean condition.
 */
fun PsiElement.findPreviousOrUpNode(p: (PsiElement) -> Boolean): PsiElement? =
    findPrevSibling(p) ?: parent?.findPreviousOrUpNode(p)

/**
 * Finds the previous declaration node before this one or null if none exists.
 */
fun PsiElement.findPreviousDeclarationNode(): BLuaDeclaration? =
    findPreviousOrUpNode { it is BLuaDeclaration } as BLuaDeclaration?

fun PsiElement.previousScope(): Map<String, PsiElement> =
    findPreviousDeclarationNode()?.scope() ?: mapOf()
