package br.com.meumetro.extensions

import java.text.Normalizer

fun String.removeAccent(text: String): String {
    val regex = Regex("[^\\p{ASCII}]")
    return Normalizer.normalize(text, Normalizer.Form.NFD).replace(regex, String())
}

fun String.stripAccents(text: String): String {
    val regex = Regex("[\\p{InCombiningDiacriticalMarks}]")
    var textFormatted = Normalizer.normalize(text, Normalizer.Form.NFD)
    textFormatted = textFormatted.replace(regex, String())
    return textFormatted
}

fun String.capitalize(text: String): String {
    return String.format("%s%s", Character.toUpperCase(text.toCharArray().first()), text.substring(1))
}