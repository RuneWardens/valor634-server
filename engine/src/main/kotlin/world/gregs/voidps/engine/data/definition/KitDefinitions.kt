import world.gregs.voidps.engine.data.definition.ItemDefinitions
import world.gregs.yaml.Yaml
import java.io.File
import java.io.FileNotFoundException

class KitDefinitions(
    private val itemDefinitions: ItemDefinitions
) {
    private val kits = mutableMapOf<String, List<String>>()

    fun loadKits(filePath: String) {
        val yaml = Yaml()
        val file = File(filePath)
        if (!file.exists()) {
            throw FileNotFoundException("File not found: $filePath")
        }
        val fileContent = file.readText(Charsets.UTF_8)
        println("Loading kits from file: $filePath")
        println("File content:\n$fileContent")
        val data = yaml.load<Map<String, Any>>(fileContent)
        @Suppress("UNCHECKED_CAST")
        kits.putAll(data["kits"] as Map<String, List<String>>)
    }

    fun getKitItems(kitName: String): List<String>? {
        return kits[kitName]
    }
}
