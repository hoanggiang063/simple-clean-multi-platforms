package coroutines

internal expect fun <T> runTest(block: suspend () -> T): T