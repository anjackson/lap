package dk.netarkivet.lap;

import java.io.IOException;

public class SessionManager implements SessionManagerInterface {

	protected WarcWriterWrapper w3;

	protected SessionConfig sessionConfig;

	public SessionManager(SessionConfig sessionConfig) {
		this.sessionConfig = sessionConfig;
	}

	@Override
	public void setWriterAgent(String writerAgent) {
		sessionConfig.writerAgent = writerAgent;
	}

	@Override
	public WarcWriterWrapper getWarcWriter(String ip) {
		if (sessionConfig.ip == null || sessionConfig.ip.equals(ip)) {
	        if (w3 == null) {
	        	w3 = WarcWriterWrapper.getWarcWriterInstance(sessionConfig);
	        }
		}
		return w3;
	}

	@Override
	public void close() throws IOException {
		if (w3 != null) {
            w3.closeWriter();
            w3.writer = null;
            w3 = null;
		}
	}

}
