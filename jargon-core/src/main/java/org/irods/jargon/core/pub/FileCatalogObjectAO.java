package org.irods.jargon.core.pub;

import org.irods.jargon.core.exception.FileNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.domain.ObjStat;

/**
 * Interface for an object that interacts with the iRODS data catalog. This
 * object is the parent of access objects that deal with iRODS collections
 * (directories) and data objects (files), and contains common operations for
 * both.
 *
 * @author Mike Conway - DICE (www.irods.org)
 *
 */
public interface FileCatalogObjectAO extends IRODSAccessObject {

	/**
	 * Given a target file, and an optional resource, retrieve the host name
	 * that is the resource server that contains the data. This is used in
	 * connection re-routing.
	 * <p/>
	 * Note that this method will return <code>null</code> if no rerouting is
	 * done
	 *
	 * @param targetAbsolutePath
	 *            <code>String</code> with the absolute path to an iRODS file or
	 *            collection
	 * @param resourceName
	 *            <code>String</code> that contains the resource name, or blank
	 *            if not used
	 * @return <code>String</code> with the iRODS host name that should be used
	 *         to connect, or null if no reconnect host is needed.
	 * @throws JargonException
	 */
	String getHostForPutOperation(String targetAbsolutePath, String resourceName)
			throws JargonException;

	/**
	 * Given a target file, and an optional resource, retrieve the host name
	 * that is the resource server that contains the data. This is used in
	 * connection re-routing.
	 * <p/>
	 * Note that this method will return <code>null</code> if no rerouting is
	 * done
	 *
	 * 
	 * @param resourceName
	 *            <code>String</code> that contains the resource name, or blank
	 *            if not used
	 * @return <code>String</code> with the iRODS host name that should be used
	 *         to connect, or null if no reconnect host is needed.
	 * @throws JargonException
	 */
	String getHostForGetOperation(String sourceAbsolutePath, String resourceName)
			throws JargonException;

	/**
	 * Given an iRODS absolute path, retrive the <code>ObjStat</code> object
	 * that represents the basic information about the iRODS file
	 *
	 * @param irodsAbsolutePath
	 *            <code>String</code> with the absolute path to the iRODS file
	 * @return {@link ObjStat} with the status information for the given iRODS
	 *         file
	 * @throws FileNotFoundException
	 *             thrown if the file obj stat operation does not return any
	 *             data
	 * @throws JargonException
	 */
	ObjStat getObjectStatForAbsolutePath(String irodsAbsolutePath)
			throws FileNotFoundException, JargonException;

	/**
	 * Check if the given user at least read access to the given iRODS absolute
	 * path
	 *
	 * @param irodsAbsolutePath
	 *            <code>String</code> with the absolute path to the iRODS file
	 * @param userName
	 *            <code>String</code> with the iRODS user name to check for
	 *            access
	 * @return <code>boolean</code> of <code>true</code> if access is available
	 * @throws JargonException
	 */
	boolean isUserHasAccess(String irodsAbsolutePath, String userName)
			throws JargonException;

}