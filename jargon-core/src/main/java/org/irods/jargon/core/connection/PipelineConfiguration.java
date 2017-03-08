/**
 *
 */
package org.irods.jargon.core.connection;

import org.irods.jargon.core.connection.ClientServerNegotiationPolicy.SslNegotiationPolicy;
import org.irods.jargon.core.protovalues.EncryptionAlgorithmEnum;

/**
 * Immutable object represents the options controlling the behavior of the io
 * pipeline. Typically, these options are built based on the current state of
 * the {@link JargonProperties} at the time a connection is created.
 * <p/>
 * Note that this object does not have synchronization. Through typical usage,
 * this configuration is initialized at connection startup, and a connection is
 * confined to one thread, so this should be just fine.
 *
 * @author Mike Conway - DICE (www.irods.org)
 *
 */
public class PipelineConfiguration {

	private final int irodsSocketTimeout;
	private final int irodsParallelSocketTimeout;
	private final int internalInputStreamBufferSize;
	private final int internalOutputStreamBufferSize;
	private final int internalCacheBufferSize;
	private final int sendInputStreamBufferSize;
	private final int localFileInputStreamBufferSize;
	private final int localFileOutputStreamBufferSize;
	private final String defaultEncoding;
	private final int inputToOutputCopyBufferByteSize;
	private final boolean reconnect;
	private final boolean instrument;
	private final boolean forcePamFlush;
	private final boolean parallelTcpKeepAlive;
	private final int parallelTcpSendWindowSize;
	private final int parallelTcpReceiveWindowSize;
	private final int parallelTcpPerformancePrefsConnectionTime;
	private final int parallelTcpPerformancePrefsLatency;
	private final int parallelTcpPerformancePrefsBandwidth;
	private final boolean primaryTcpKeepAlive;
	private final int primaryTcpSendWindowSize;
	private final int primaryTcpReceiveWindowSize;
	private final int primaryTcpPerformancePrefsConnectionTime;
	private final int primaryTcpPerformancePrefsLatency;
	private final int primaryTcpPerformancePrefsBandwidth;
	private final int socketRenewalIntervalInSeconds;
	/**
	 * Default SSL negotiation policy, may be overrideen per request in the
	 * IRODSAccount
	 */
	private final SslNegotiationPolicy negotiationPolicy;
	/**
	 * Encryption algo for parallel transfers
	 */
	private final EncryptionAlgorithmEnum encryptionAlgorithmEnum;
	/**
	 * Key size for encryption of parallel transfers when SSL negotiated
	 */
	private final int encryptionKeySize;
	/**
	 * Salt size for encryption of parallel transfers when SSL negotiated
	 */
	private final int encryptionSaltSize;

	/**
	 * Number of hash rounds for encryption of parallel transfers when SSL
	 * negotiated
	 */
	private final int encryptionNumberHashRounds;

	/**
	 * Static initializer method will derive an immutable
	 * <code>PipelineConfiguration</code> based on the prevailing
	 * <code>JargonProperties</code> at the time the connection is created.
	 *
	 * @param jargonProperties
	 * @return {@link PipelineConfiguration}
	 */
	public static PipelineConfiguration instance(
			final JargonProperties jargonProperties) {
		return new PipelineConfiguration(jargonProperties);
	}

	private PipelineConfiguration(final JargonProperties jargonProperties) {

		if (jargonProperties == null) {
			throw new IllegalArgumentException("null jargonProperties");
		}

		irodsSocketTimeout = jargonProperties.getIRODSSocketTimeout();
		irodsParallelSocketTimeout = jargonProperties
				.getIRODSParallelTransferSocketTimeout();
		internalInputStreamBufferSize = jargonProperties
				.getInternalInputStreamBufferSize();
		internalOutputStreamBufferSize = jargonProperties
				.getInternalOutputStreamBufferSize();
		internalCacheBufferSize = jargonProperties.getInternalCacheBufferSize();
		sendInputStreamBufferSize = jargonProperties
				.getSendInputStreamBufferSize();
		localFileInputStreamBufferSize = jargonProperties
				.getLocalFileInputStreamBufferSize();
		localFileOutputStreamBufferSize = jargonProperties
				.getLocalFileOutputStreamBufferSize();
		inputToOutputCopyBufferByteSize = jargonProperties
				.getInputToOutputCopyBufferByteSize();
		instrument = jargonProperties.isInstrument();
		reconnect = jargonProperties.isReconnect();
		defaultEncoding = jargonProperties.getEncoding();
		forcePamFlush = jargonProperties.isForcePamFlush();

		parallelTcpKeepAlive = jargonProperties.isParallelTcpKeepAlive();
		parallelTcpPerformancePrefsBandwidth = jargonProperties
				.getParallelTcpPerformancePrefsBandwidth();
		parallelTcpPerformancePrefsConnectionTime = jargonProperties
				.getParallelTcpPerformancePrefsConnectionTime();
		parallelTcpPerformancePrefsLatency = jargonProperties
				.getParallelTcpPerformancePrefsLatency();
		parallelTcpReceiveWindowSize = jargonProperties
				.getParallelTcpReceiveWindowSize();
		parallelTcpSendWindowSize = jargonProperties
				.getParallelTcpSendWindowSize();

		primaryTcpKeepAlive = jargonProperties.isPrimaryTcpKeepAlive();
		primaryTcpPerformancePrefsBandwidth = jargonProperties
				.getPrimaryTcpPerformancePrefsBandwidth();
		primaryTcpPerformancePrefsConnectionTime = jargonProperties
				.getPrimaryTcpPerformancePrefsConnectionTime();
		primaryTcpPerformancePrefsLatency = jargonProperties
				.getPrimaryTcpPerformancePrefsLatency();
		primaryTcpReceiveWindowSize = jargonProperties
				.getPrimaryTcpReceiveWindowSize();
		primaryTcpSendWindowSize = jargonProperties
				.getPrimaryTcpSendWindowSize();
		socketRenewalIntervalInSeconds = jargonProperties
				.getSocketRenewalIntervalInSeconds();
		negotiationPolicy = jargonProperties.getNegotiationPolicy();
		encryptionAlgorithmEnum = jargonProperties.getEncryptionAlgorithmEnum();
		encryptionKeySize = jargonProperties.getEncryptionKeySize();
		encryptionNumberHashRounds = jargonProperties
				.getEncryptionNumberHashRounds();
		encryptionSaltSize = jargonProperties.getEncryptionSaltSize();

	}

	/**
	 * @return the internalInputStreamBufferSize
	 */
	public int getInternalInputStreamBufferSize() {
		return internalInputStreamBufferSize;
	}

	/**
	 * @return the internalOutputStreamBufferSize
	 */
	public int getInternalOutputStreamBufferSize() {
		return internalOutputStreamBufferSize;
	}

	/**
	 * @return the internalCacheBufferSize
	 */
	public int getInternalCacheBufferSize() {
		return internalCacheBufferSize;
	}

	/**
	 * @return the sendInputStreamBufferSize
	 */
	public int getSendInputStreamBufferSize() {
		return sendInputStreamBufferSize;
	}

	/**
	 * @return the localFileOutputStreamBufferSize
	 */
	public int getLocalFileOutputStreamBufferSize() {
		return localFileOutputStreamBufferSize;
	}

	/**
	 * @return the irodsSocketTimeout
	 */
	public int getIrodsSocketTimeout() {
		return irodsSocketTimeout;
	}

	/**
	 * @return the irodsParallelSocketTimeout
	 */
	public int getIrodsParallelSocketTimeout() {
		return irodsParallelSocketTimeout;
	}

	/**
	 * @return the defaultEncoding
	 */
	public String getDefaultEncoding() {
		return defaultEncoding;
	}

	/**
	 * @return the inputToOutputCopyBufferByteSize
	 */
	public int getInputToOutputCopyBufferByteSize() {
		return inputToOutputCopyBufferByteSize;
	}

	/**
	 * @return the localFileInputStreamBufferSize
	 */
	public int getLocalFileInputStreamBufferSize() {
		return localFileInputStreamBufferSize;
	}

	/**
	 * @return <code>boolean</code> indicates whether to reconnect to avoid some
	 *         firewall issues. This is equivalent to the -T option on the
	 *         put/get operations in iCommands
	 */
	public boolean isReconnect() {
		return reconnect;
	}

	/**
	 * @return <code>boolean</code> indicates whether to incorporate detailed
	 *         statistics in the DEBUG log regarding performance metrics, useful
	 *         for tuning and optimization, with the potential to add overhead,
	 *         so typically not suitable for production
	 */
	public boolean isInstrument() {
		return instrument;
	}

	/**
	 * @return the forcePamFlush
	 */
	synchronized boolean isForcePamFlush() {
		return forcePamFlush;
	}

	public boolean isParallelTcpKeepAlive() {
		return parallelTcpKeepAlive;
	}

	public int getParallelTcpSendWindowSize() {
		return parallelTcpSendWindowSize;
	}

	public int getParallelTcpReceiveWindowSize() {
		return parallelTcpReceiveWindowSize;
	}

	public int getParallelTcpPerformancePrefsConnectionTime() {
		return parallelTcpPerformancePrefsConnectionTime;
	}

	public int getParallelTcpPerformancePrefsLatency() {
		return parallelTcpPerformancePrefsLatency;
	}

	public int getParallelTcpPerformancePrefsBandwidth() {
		return parallelTcpPerformancePrefsBandwidth;
	}

	public boolean isPrimaryTcpKeepAlive() {
		return primaryTcpKeepAlive;
	}

	public int getPrimaryTcpSendWindowSize() {
		return primaryTcpSendWindowSize;
	}

	public int getPrimaryTcpReceiveWindowSize() {
		return primaryTcpReceiveWindowSize;
	}

	public int getPrimaryTcpPerformancePrefsConnectionTime() {
		return primaryTcpPerformancePrefsConnectionTime;
	}

	public int getPrimaryTcpPerformancePrefsLatency() {
		return primaryTcpPerformancePrefsLatency;
	}

	public int getPrimaryTcpPerformancePrefsBandwidth() {
		return primaryTcpPerformancePrefsBandwidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PipelineConfiguration [irodsSocketTimeout=");
		builder.append(irodsSocketTimeout);
		builder.append(", irodsParallelSocketTimeout=");
		builder.append(irodsParallelSocketTimeout);
		builder.append(", internalInputStreamBufferSize=");
		builder.append(internalInputStreamBufferSize);
		builder.append(", internalOutputStreamBufferSize=");
		builder.append(internalOutputStreamBufferSize);
		builder.append(", internalCacheBufferSize=");
		builder.append(internalCacheBufferSize);
		builder.append(", sendInputStreamBufferSize=");
		builder.append(sendInputStreamBufferSize);
		builder.append(", localFileInputStreamBufferSize=");
		builder.append(localFileInputStreamBufferSize);
		builder.append(", localFileOutputStreamBufferSize=");
		builder.append(localFileOutputStreamBufferSize);
		builder.append(", ");
		if (defaultEncoding != null) {
			builder.append("defaultEncoding=");
			builder.append(defaultEncoding);
			builder.append(", ");
		}
		builder.append("inputToOutputCopyBufferByteSize=");
		builder.append(inputToOutputCopyBufferByteSize);
		builder.append(", reconnect=");
		builder.append(reconnect);
		builder.append(", instrument=");
		builder.append(instrument);
		builder.append(", forcePamFlush=");
		builder.append(forcePamFlush);
		builder.append(", parallelTcpKeepAlive=");
		builder.append(parallelTcpKeepAlive);
		builder.append(", parallelTcpSendWindowSize=");
		builder.append(parallelTcpSendWindowSize);
		builder.append(", parallelTcpReceiveWindowSize=");
		builder.append(parallelTcpReceiveWindowSize);
		builder.append(", parallelTcpPerformancePrefsConnectionTime=");
		builder.append(parallelTcpPerformancePrefsConnectionTime);
		builder.append(", parallelTcpPerformancePrefsLatency=");
		builder.append(parallelTcpPerformancePrefsLatency);
		builder.append(", parallelTcpPerformancePrefsBandwidth=");
		builder.append(parallelTcpPerformancePrefsBandwidth);
		builder.append(", primaryTcpKeepAlive=");
		builder.append(primaryTcpKeepAlive);
		builder.append(", primaryTcpSendWindowSize=");
		builder.append(primaryTcpSendWindowSize);
		builder.append(", primaryTcpReceiveWindowSize=");
		builder.append(primaryTcpReceiveWindowSize);
		builder.append(", primaryTcpPerformancePrefsConnectionTime=");
		builder.append(primaryTcpPerformancePrefsConnectionTime);
		builder.append(", primaryTcpPerformancePrefsLatency=");
		builder.append(primaryTcpPerformancePrefsLatency);
		builder.append(", primaryTcpPerformancePrefsBandwidth=");
		builder.append(primaryTcpPerformancePrefsBandwidth);
		builder.append(", socketRenewalIntervalInSeconds=");
		builder.append(socketRenewalIntervalInSeconds);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the socketRenewalIntervalInSeconds
	 */
	public int getSocketRenewalIntervalInSeconds() {
		return socketRenewalIntervalInSeconds;
	}

	public SslNegotiationPolicy getNegotiationPolicy() {
		return negotiationPolicy;
	}

	public EncryptionAlgorithmEnum getEncryptionAlgorithmEnum() {
		return encryptionAlgorithmEnum;
	}

	public int getEncryptionKeySize() {
		return encryptionKeySize;
	}

	public int getEncryptionSaltSize() {
		return encryptionSaltSize;
	}

	public int getEncryptionNumberHashRounds() {
		return encryptionNumberHashRounds;
	}

}
