package main.analytics.models.parameters;

import main.analytics.exceptions.InvalidIdValueException;

/**
 * A class that represents the question entity, that is defined by service_id and variation_id.
 * <p>
 * Instances of this class contain data stored in the second word of input lines.
 * The company provides 10 different services, each with 3 variations.
 */
public class Service {

    /**
     * A constant of maximum amount of services provided by the company.
     */
    private static final int MAX_SERVICE_ID = 10;
    /**
     * A constant of maximum amount of service variations provided by the company.
     */
    private static final int MAX_VARIATION_ID = 3;
    /**
     * ID of the service. Can be null when used in {@link main.analytics.models.lines.QueryLine}.
     */
    private Integer service_id = null;
    /**
     * ID of the service variation. May not be specified.
     */
    private Integer variation_id = null;

    /**
     * Class constructor.
     */
    public Service(){}

    /**
     * Method compares the service criteria for data line and query line.
     * @param dataService instance of Service in the data line that is being checked on meeting criteria.
     * @return            returns true if the record matches the criteria
     */
    public boolean isValidForDataLine(Service dataService) {
        if (this.service_id == null)
            return true;
        if (this.variation_id == null)
            return this.service_id.equals(dataService.service_id);
        else return this.service_id.equals(dataService.service_id) &&
                this.variation_id.equals(dataService.variation_id);
    }

    /**
     * Method for input values validation. service_id cannot be greater than 10, variation_id cannot be greater than 3.
     * @param value   a value that is being assigned to a field
     * @param id_type specifies the field
     * @return        returns true if all values are valid
     */
    private boolean isValueInvalid(int value, int id_type) {
        if (value < 1)
            return true;
        switch (id_type) {
            case 1:
                return value > MAX_SERVICE_ID;
            case 2:
                return value > MAX_VARIATION_ID;
            default:
                return true;
        }
    }

    public Integer getService_id() {
        return service_id;
    }

    /**
     * Setter for service_id. Checks if the value (must be > 0 and <= 10) is valid before setting it.
     * @param service_id
     * @throws InvalidIdValueException
     */
    public void setService_id(Integer service_id) throws InvalidIdValueException {
        if(isValueInvalid(service_id, 1))
            throw new InvalidIdValueException("There are only 10 services.");
        this.service_id = service_id;
    }

    public Integer getVariation_id() {
        return variation_id;
    }

    /**
     * Setter for variation_id. Checks if the value (must be > 0 and <= 3) is valid before setting it.
     * @param variation_id
     * @throws InvalidIdValueException
     */
    public void setVariation_id(Integer variation_id) throws InvalidIdValueException {
        if(isValueInvalid(variation_id, 2))
            throw new InvalidIdValueException("There are only 10 variations.");
        this.variation_id = variation_id;
    }

    @Override
    public String toString() {
        return "Service{" +
                "service_id=" + service_id +
                ", variation_id=" + variation_id +
                '}';
    }
}
