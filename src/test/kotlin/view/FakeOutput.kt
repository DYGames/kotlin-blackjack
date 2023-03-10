package view

class FakeOutput(private val onOutput: (output: Any?) -> Unit) : Output {
    override fun print(message: Any?) {
        onOutput(message)
    }

    override fun println(message: Any?) {
        onOutput(message)
        onOutput("\n")
    }
}
