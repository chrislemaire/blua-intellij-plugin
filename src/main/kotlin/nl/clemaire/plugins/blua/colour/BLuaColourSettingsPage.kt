package nl.clemaire.plugins.blua.colour

import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.options.colors.AttributesDescriptor

import com.intellij.openapi.editor.colors.TextAttributesKey

import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.ColorDescriptor
import nl.clemaire.plugins.blua.BLuaIcons
import javax.swing.Icon


class BLuaColourSettingsPage : ColorSettingsPage {
    private val descriptors = arrayOf(
        AttributesDescriptor("Key", BLuaSyntaxHighlighter.key),
        AttributesDescriptor("Separator", BLuaSyntaxHighlighter.separator),
        AttributesDescriptor("Value", BLuaSyntaxHighlighter.value),
        AttributesDescriptor("Bad value", BLuaSyntaxHighlighter.badCharacter)
    )

    override fun getIcon(): Icon = BLuaIcons.fileIcon

    override fun getHighlighter(): SyntaxHighlighter = BLuaSyntaxHighlighter()

    override fun getDemoText(): String = """# You are reading the ".properties" entry.
            |! The ``exclamation mark can also mark text as comments.
            |website = https://en.wikipedia.org/
            |language = English
            |# The backslash below tells the application to continue reading
            |# the value onto the next line.
            |message = Welcome to \
            |          Wikipedia!
            |# Add spaces to the key
            |key\ with\ spaces = This is the value that could be looked up with the key "key with spaces".
            |# Unicode
            |tab : \u``0009""".trimMargin()

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String?, TextAttributesKey?>? =
        null

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = descriptors

    override fun getColorDescriptors(): Array<ColorDescriptor?> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String = "BLua"
}
