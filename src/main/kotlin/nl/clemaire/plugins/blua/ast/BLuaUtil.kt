@file:JvmName("BLuaUtil")

package nl.clemaire.plugins.blua.ast

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import nl.clemaire.plugins.blua.ast.ext.Scoped

/**
 * Finds a previous sibling that satisfies the given boolean condition.
 */
fun PsiElement.findPreviousSibling(p: (PsiElement) -> Boolean): PsiElement? {
    var current = prevSibling
    while (current != null && !p(current)) {
        current = current.prevSibling
    }
    return current
}

/**
 * Finds a previous sibling or upper previous sibling or parent that satisfies the given boolean condition.
 */
fun PsiElement.findPreviousSiblingOrParentNode(p: (PsiElement) -> Boolean): PsiElement? =
    findPreviousSibling(p) ?: parent?.findPreviousSiblingOrParentNode(p)

/**
 * Finds the previous declaration node before this one or null if none exists.
 */
fun PsiElement.getPreviousDeclarationNode(): Scoped? =
    findPreviousSiblingOrParentNode { it is Scoped } as Scoped?

/**
 * Finds the full scope of the last scope changing declaration.
 */
fun PsiElement.getPreviousScope(): Map<String, PsiElement> =
    getPreviousDeclarationNode()?.scope() ?: mapOf()
