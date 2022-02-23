package main.analytics.models.parameters;

import main.analytics.exceptions.InvalidIdValueException;

public class Question {

    private static final int MAX_QUESTION_TYPE_ID = 10;
    private static final int MAX_CATEGORY_ID = 20;
    private static final int MAX_SUB_CATEGORY_ID = 5;

    private Integer question_type_id = null;
    private Integer category_id = null;
    private Integer sub_category_id = null;

    public Question(){}

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

    public void setQuestion_type_id(Integer question_type_id) throws InvalidIdValueException {
        if(isValueInvalid(question_type_id, 1))
            throw new InvalidIdValueException("There are only 10 types of questions.");
        else this.question_type_id = question_type_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) throws InvalidIdValueException {
        if(isValueInvalid(category_id, 2))
            throw new InvalidIdValueException("There are only 20 categories.");
        else this.category_id = category_id;
    }

    public Integer getSub_category_id() {
        return sub_category_id;
    }

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
