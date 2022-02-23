package main.analytics.models.parameters;

import main.analytics.exceptions.InvalidIdValueException;

/**
 * A class that represents the question entity, that is defined by question_type_id, category_id and sub_category_id
 * <p>
 * Instances of this class contain data stored in the third word of input lines.
 * Questions asked by customers are divided into 10 types,
 * each can belong to 20 categories, a category can have 5 subcategories.
 */
public class Question {

    /**
     * A constant of maximum amount of question types.
     */
    private static final int MAX_QUESTION_TYPE_ID = 10;
    /**
     * A constant of maximum amount of question categories.
     */
    private static final int MAX_CATEGORY_ID = 20;
    /**
     * A constant of maximum amount of question subcategories.
     */
    private static final int MAX_SUB_CATEGORY_ID = 5;
    /**
     * ID of question type. Can be null when used in {@link main.analytics.models.lines.QueryLine}.
     */
    private Integer question_type_id = null;
    /**
     * ID of question category. May not be specified.
     */
    private Integer category_id = null;
    /**
     * ID of question subcategory. May not be specified.
     */
    private Integer sub_category_id = null;

    /**
     * Class constructor.
     */
    public Question(){}

    /**
     * Method compares the question criteria for data line and query line.
     * @param dataQuestion instance of Question in the data line that is being checked on meeting criteria.
     * @return             returns true if the record matches the criteria
     */
    public boolean isValidForDataLine(Question dataQuestion) {
        if(this.question_type_id == null)
            return true;
        if (this.category_id == null) {
            return this.question_type_id.equals(dataQuestion.question_type_id);
        }
        else if (this.sub_category_id == null) {
            return this.question_type_id.equals(dataQuestion.question_type_id) &&
                    this.category_id.equals(dataQuestion.category_id);
        }
        else return this.question_type_id.equals(dataQuestion.question_type_id) &&
                    this.category_id.equals(dataQuestion.category_id) &&
                    this.sub_category_id.equals(dataQuestion.sub_category_id);
    }

    /**
     * Method for input values validation. question_type_id cannot be greater than 10,
     * category_id cannot be greater than 20, sub_category_id cannot be greater than 5.
     * @param value   a value that is being assigned to a field
     * @param id_type specifies the field
     * @return        returns true if all values are valid
     */
    private boolean isValueInvalid(int value, int id_type){
        if(value < 1)
            return true;
        switch (id_type){
            case 1:
                return value > MAX_QUESTION_TYPE_ID;
            case 2:
                return value > MAX_CATEGORY_ID;
            case 3:
                return value > MAX_SUB_CATEGORY_ID;
            default:
                return true;
        }
    }

    public Integer getQuestion_type_id() {
        return question_type_id;
    }

    /**
     * Setter for question_type_id. Checks if the value (must be > 0 and <= 10) is valid before setting it.
     * @param question_type_id
     * @throws InvalidIdValueException
     */
    public void setQuestion_type_id(Integer question_type_id) throws InvalidIdValueException {
        if(isValueInvalid(question_type_id, 1))
            throw new InvalidIdValueException("There are only 10 types of questions.");
        else this.question_type_id = question_type_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    /**
     * Setter for category_id. Checks if the value (must be > 0 and <= 20) is valid before setting it.
     * @param category_id
     * @throws InvalidIdValueException
     */
    public void setCategory_id(Integer category_id) throws InvalidIdValueException {
        if(isValueInvalid(category_id, 2))
            throw new InvalidIdValueException("There are only 20 categories.");
        else this.category_id = category_id;
    }

    public Integer getSub_category_id() {
        return sub_category_id;
    }

    /**
     * Setter for sub_category_id. Checks if the value (must be > 0 and <= 5) is valid before setting it.
     * @param sub_category_id
     * @throws InvalidIdValueException
     */
    public void setSub_category_id(Integer sub_category_id) throws InvalidIdValueException {
        if(isValueInvalid(sub_category_id, 3))
            throw new InvalidIdValueException("There are only 5 sub-categories.");
        else this.sub_category_id = sub_category_id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question_type_id=" + question_type_id +
                ", category_id=" + category_id +
                ", sub_category_id=" + sub_category_id +
                '}';
    }
}
