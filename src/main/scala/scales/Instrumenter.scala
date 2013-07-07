package scales

import java.util.concurrent.atomic.AtomicInteger
import scala.collection.mutable.ListBuffer
import scala.reflect.internal.util.SourceFile
import scala.collection.mutable

/** @author Stephen Samuel */
object Instrumentation {

    val ids = new AtomicInteger(0)
    val coverage = new Coverage

    def add(source: SourceFile, _package: String, _class: String, _method: String, start: Int, line: Int) = {
        val id = ids.incrementAndGet()
        val stmt = MeasuredStatement(source, _package, _class, _method: String, id, start, line)
        coverage.add(stmt)
        stmt
    }

    def invoked(id: Int): Int = {
        coverage.invoked(id)
        id
    }
}

sealed trait LineStatus
case object Covered extends LineStatus
case object MissingCoverage extends LineStatus
case object NotInstrumented extends LineStatus

