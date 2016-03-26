package io.codearte.accurest.stubrunner

import spock.lang.Specification

class BatchStubRunnerSpec extends Specification {

	static final String KNOWN_STUB_PATH = 'group:knownArtifact'
	static final String UNKNOWN_STUB_PATH = 'group:unknownArtifact'
	static final URL KNOWN_STUB_URL = new URL('http://localhost:8080')

	def 'should provide stub URL from enclosed stub runner'() {
		given:
		BatchStubRunner batchStubRunner = new BatchStubRunner(runners())
		expect:
		batchStubRunner.findStubUrl(KNOWN_STUB_PATH) == KNOWN_STUB_URL
	}

	def 'should return empty optional for unknown stub path'() {
		given:
		BatchStubRunner batchStubRunner = new BatchStubRunner(runners())
		expect:
		!batchStubRunner.findStubUrl(UNKNOWN_STUB_PATH)
	}

	Collection<StubRunner> runners() {
		StubRunner runner = Mock(StubRunner)
		runner.findStubUrl("group", "knownArtifact") >> KNOWN_STUB_URL
		runner.findStubUrl("group", "unknownArtifact") >> null
		return [runner]
	}

}
