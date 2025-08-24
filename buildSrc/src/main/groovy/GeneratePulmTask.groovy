import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import com.thoughtworks.qdox.JavaProjectBuilder

abstract class GeneratePumlTask extends DefaultTask {

    @InputDirectory
    @PathSensitive(PathSensitivity.RELATIVE)
    abstract DirectoryProperty getInputDir()

    @OutputFile
    abstract RegularFileProperty getOutputFile()

    @TaskAction
    void generate() {
        def builder = new com.thoughtworks.qdox.JavaProjectBuilder()
        builder.addSourceTree(getInputDir().get().asFile)

        def outFile = getOutputFile().get().asFile
        outFile.parentFile.mkdirs()
        outFile.text = "@startuml\nskinparam classAttributeIconSize 0\n"

        builder.classes.each { clazz ->
            def type = clazz.interface ? "interface" : "class"
            outFile.append("${type} ${clazz.fullyQualifiedName} {\n")

            clazz.methods.each { m ->
                def visibility = m.modifiers.contains("public") ? "+" :
                                m.modifiers.contains("protected") ? "#" :
                                m.modifiers.contains("private") ? "-" : "~"
                def returnType = m.returnType?.genericValue ?: "void"
                outFile.append("  ${visibility} ${returnType} ${m.name}()\n")
            }

            outFile.append("}\n")

            // ereditarietà
            if (clazz.superClass != null && clazz.superClass.fullyQualifiedName != "java.lang.Object") {
                outFile.append("${clazz.fullyQualifiedName} --|> ${clazz.superClass.fullyQualifiedName}\n")
            }


            // interfacce implementate
            clazz.interfaces.each { i ->
                outFile.append("${clazz.fullyQualifiedName} ..|> ${i.fullyQualifiedName}\n")
            }
        }

        outFile.append("@enduml\n")
        println "Creato ${outFile} con ${builder.classes.size()} classi."
    }

}

