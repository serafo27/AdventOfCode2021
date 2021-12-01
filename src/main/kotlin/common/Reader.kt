package common

import java.io.File
import java.lang.ClassLoader.getSystemClassLoader

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Reader {
  fun readFile(path: String): List<String>
      = File(getSystemClassLoader().getResource(path).path)
          .useLines { it.toList() }
}
