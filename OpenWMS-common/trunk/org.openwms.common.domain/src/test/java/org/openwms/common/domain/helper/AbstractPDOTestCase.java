/*
 * OpenWMS, the open Warehouse Management System
 * 
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package org.openwms.common.domain.helper;

import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * 
 * A AbstractPDOTestCase.
 * 
 * @author <a href="mailto:openwms@googlemail.com">Heiko Scherrer</a>
 * @version $Revision$
 */
public abstract class AbstractPDOTestCase {

	protected final Log LOG = LogFactory.getLog(this.getClass());
	protected static EntityManagerFactory emf;
	protected static EntityManager em;
	protected static boolean running = false;

	/**
	 * Do before test run.
	 * 
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			if (!running) {
				running = true;
			}
			if (!Helper.getInstance().isDbStarted() && !Helper.getInstance().isPersistenceUnitDurable()) {
				Helper.getInstance().startDb();
			}
			emf = Helper.getInstance().createEntityManagerFactory(Helper.getInstance().getPersistenceUnit());
		}
		catch (Exception ex) {
			fail("Exception during JPA EntityManager instanciation: " + ex.getMessage());
		}
	}

	/**
	 * Do after test run.
	 * 
	 */
	@AfterClass
	public static void tearDownAfterClass() {
		if (em != null) {
			em.close();
		}
		if (emf != null) {
			emf.close();
		}
	}

	@After
	public void tearDown() {
		EntityTransaction entityTransaction = em.getTransaction();
		if (entityTransaction.isActive()) {
			LOG.debug("Rollback transaction");
			entityTransaction.rollback();
		}
		LOG.debug("Destroying EntityManager");
		em.close();
		em = null;
	}

	@Before
	public void setUp() {
		if (em == null) {
			LOG.debug("Getting EntityManager");
			em = emf.createEntityManager();
			if (em==null) {
				LOG.debug("Creation of EntityManager failed");
				throw new RuntimeException("TEST manage is null");
			} else {
				LOG.debug("Creation of EntityManager passed");
			}
		}
	}
}