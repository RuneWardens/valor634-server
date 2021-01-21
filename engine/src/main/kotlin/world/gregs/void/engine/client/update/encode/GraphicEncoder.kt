package world.gregs.void.engine.client.update.encode

import world.gregs.void.buffer.Endian
import world.gregs.void.buffer.Modifier
import world.gregs.void.buffer.write.Writer
import world.gregs.void.engine.entity.character.update.VisualEncoder
import world.gregs.void.engine.entity.character.update.visual.Graphic
import world.gregs.void.utility.func.toInt

/**
 * @author GregHib <greg@gregs.world>
 * @since April 25, 2020
 */
class GraphicEncoder(private val npc: Boolean, private val index: Int, mask: Int) : VisualEncoder<Graphic>(mask) {

    override fun encode(writer: Writer, visual: Graphic) {
        val (id, delay, height, rotation, forceRefresh) = visual
        writer.apply {
            val trajectory = (delay and 0xffff) or (height shl 16)
            val slot = 0
            val details = (rotation and 0x7) or (slot shl 3) or (forceRefresh.toInt() shl 7)
            writeShort(
                id,
                if (npc && index == 1 || !npc && index != 0) Modifier.ADD else Modifier.NONE,
                if (npc && index == 0 || !npc && index != 0) Endian.LITTLE else Endian.BIG
            )
            writeInt(
                trajectory,
                if (npc && index == 0 || !npc && index != 0) Modifier.INVERSE else Modifier.NONE,
                if (npc && index == 0 || !npc && index != 0) Endian.MIDDLE
                else if (npc) Endian.BIG else Endian.LITTLE
            )
            writeByte(
                details,
                if (npc && index == 0 || !npc && index != 0) Modifier.INVERSE
                else if (npc && index == 2 || !npc) Modifier.ADD else Modifier.NONE
            )
        }
    }

}