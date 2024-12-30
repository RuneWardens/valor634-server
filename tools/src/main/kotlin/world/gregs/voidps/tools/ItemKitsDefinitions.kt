package world.gregs.voidps.tools

import world.gregs.yaml.Yaml
import java.io.File
import java.util.Properties

object ItemKitsDefinitions {

    @JvmStatic
    fun main(args: Array<String>) {
        // Load the kits definitions from kits.yml
        val yaml = Yaml()
        val kitsDefinitions = KitsDefinitions().load(yaml, property("kitsDefinitionsPath"))

        // Print each kit and its items
        kitsDefinitions.forEach { (kitName, items) ->
            println("Kit: $kitName")
            println("Items: ${items.joinToString(", ")}")
        }
    }
}

class KitsDefinitions {
    /**
     * Loads the kits.yml file and parses it into a Map<String, List<String>>.
     * @param yaml The YAML parser instance.
     * @param path The file path to kits.yml.
     * @return A map containing the kits and their respective items.
     */
    fun load(yaml: Yaml, path: String): Map<String, List<String>> {
        val kitsFile = File(path)
        if (!kitsFile.exists() || !kitsFile.isFile) {
            throw RuntimeException("kits.yml file not found at path: ${kitsFile.absolutePath}")
        }

        val fileContent = kitsFile.readText()
        println("File content of kits.yml: $fileContent")

        val rawData = yaml.load<Map<String, Any>>(fileContent)

        val kitsSection = rawData["kits"] as? Map<String, List<String>>
            ?: throw IllegalStateException("Missing or malformed 'kits' section in kits.yml.")

        return kitsSection.mapValues { it.value.map { item -> item.toString() } }
    }
}

/**
 * Retrieves a property value from the properties file.
 * @param key The property key.
 * @return The property value.
 */
fun property(key: String): String {
    val properties = loadProperties()
    return properties.getProperty(key)
        ?: throw RuntimeException("Property '$key' not found in properties file.")
}

/**
 * Loads the properties from the properties file.
 * @return A Properties object containing the loaded properties.
 */
fun loadProperties(): Properties {
    val properties = Properties()
    val propertiesFile = File("path/to/test.properties") // Replace with the actual path to your test.properties file
    if (!propertiesFile.exists()) {
        throw RuntimeException("Properties file not found at: ${propertiesFile.absolutePath}")
    }
    properties.load(propertiesFile.inputStream())
    return properties
}
