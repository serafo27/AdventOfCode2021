package common

import java.io.File

class Reader {
  fun readFile(path: String): List<String>
      = File(path).useLines { it.toList() }
}
