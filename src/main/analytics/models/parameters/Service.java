package main.analytics.models.parameters;

import main.analytics.exceptions.InvalidIdValueException;

public class Service {

    private static final int MAX_SERVICE_ID = 10;
    private static final int MAX_VARIATION_ID = 3;

    private Integer service_id = null;
    private Integer variation_id = null;

    public Service(){}

    public boolean isValidForDataLine(Service dataService) {
        if (this.service_id == null)
            return true;
        if (this.variation_id == null)
            return this.service_id.equals(dataService.service_id);
        else return this.service_id.equals(dataService.service_id) &&
                this.variation_id.equals(dataService.variation_id);
    }

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

    public void setService_id(Integer service_id) throws InvalidIdValueException {
        if(isValueInvalid(service_id, 1))
            throw new InvalidIdValueException("There are only 10 services.");
        this.service_id = service_id;
    }

    public Integer getVariation_id() {
        return variation_id;
    }

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
