package org.isf.pricesothers.manager;

import java.util.ArrayList;
import java.util.List;

import org.isf.generaldata.MessageBundle;
import org.isf.menu.manager.Context;
import org.isf.pricesothers.model.PricesOthers;
import org.isf.pricesothers.service.PriceOthersIoOperations;
import org.isf.utils.exception.OHServiceException;
import org.isf.utils.exception.OHServiceValidationException;
import org.isf.utils.exception.model.OHExceptionMessage;
import org.isf.utils.exception.model.OHSeverityLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class PricesOthersManager {

	private final Logger logger = LoggerFactory.getLogger(PricesOthersManager.class);
	
	private PriceOthersIoOperations ioOperations = Context.getApplicationContext().getBean(PriceOthersIoOperations.class);

	/**
	 * return the list of {@link PriceOthers}s in the DB
	 * 
	 * @return the list of {@link PriceOthers}s
	 * @throws OHServiceException 
	 */
	public ArrayList<PricesOthers> getOthers() throws OHServiceException {
        return ioOperations.getOthers();
	}

	/**
	 * insert a new {@link PriceOthers} in the DB
	 * 
	 * @param other - the {@link PriceOthers} to insert
	 * @return <code>true</code> if the list has been inserted, <code>false</code> otherwise
	 * @throws OHServiceException 
	 */
	public boolean newOther(PricesOthers other) throws OHServiceException {
        validatePricesOthers(other);
        return ioOperations.newOthers(other);
	}

	/**
	 * delete a {@link PriceOthers} in the DB
	 * 
	 * @param other - the {@link PriceOthers} to delete
	 * @return <code>true</code> if the list has been deleted, <code>false</code> otherwise
	 * @throws OHServiceException 
	 */
	public boolean deleteOther(PricesOthers other) throws OHServiceException {
        return ioOperations.deleteOthers(other);
	}

	/**
	 * update a {@link PriceOthers} in the DB
	 * 
	 * @param other - the {@link PriceOthers} to update
	 * @return <code>true</code> if the list has been updated, <code>false</code> otherwise
	 * @throws OHServiceException 
	 */
	public boolean updateOther(PricesOthers other) throws OHServiceException {
        validatePricesOthers(other);
        return ioOperations.updateOther(other);
	}

	/**
	 * Verify if the object is valid for CRUD and return a list of errors, if any
	 * @param pricesOthers
	 * @throws OHServiceValidationException 
	 */
    protected void validatePricesOthers(PricesOthers pricesOthers) throws OHServiceValidationException {
        List<OHExceptionMessage> errors = new ArrayList<OHExceptionMessage>();

        if (StringUtils.isEmpty(pricesOthers.getCode())) {  //$NON-NLS-1$
            errors.add(new OHExceptionMessage(MessageBundle.getMessage("angal.hospital"),
                    MessageBundle.getMessage("angal.pricesothers.pleaseinsertacode"),
                    OHSeverityLevel.ERROR));
        }
        if (StringUtils.isEmpty(pricesOthers.getDescription())) {  //$NON-NLS-1$
            errors.add(new OHExceptionMessage(MessageBundle.getMessage("angal.hospital"),
                    MessageBundle.getMessage("angal.pricesothers.pleaseinsertadescription"),
                    OHSeverityLevel.ERROR));
        }
        if(!errors.isEmpty()){
	        throw new OHServiceValidationException(errors);
	    }
    }
	
}