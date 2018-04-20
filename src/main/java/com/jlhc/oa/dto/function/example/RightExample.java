package com.jlhc.oa.dto.function.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RightExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public RightExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRightIdIsNull() {
            addCriterion("right_id is null");
            return (Criteria) this;
        }

        public Criteria andRightIdIsNotNull() {
            addCriterion("right_id is not null");
            return (Criteria) this;
        }

        public Criteria andRightIdEqualTo(Integer value) {
            addCriterion("right_id =", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdNotEqualTo(Integer value) {
            addCriterion("right_id <>", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdGreaterThan(Integer value) {
            addCriterion("right_id >", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("right_id >=", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdLessThan(Integer value) {
            addCriterion("right_id <", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdLessThanOrEqualTo(Integer value) {
            addCriterion("right_id <=", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdIn(List<Integer> values) {
            addCriterion("right_id in", values, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdNotIn(List<Integer> values) {
            addCriterion("right_id not in", values, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdBetween(Integer value1, Integer value2) {
            addCriterion("right_id between", value1, value2, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdNotBetween(Integer value1, Integer value2) {
            addCriterion("right_id not between", value1, value2, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityIsNull() {
            addCriterion("right_group_identity is null");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityIsNotNull() {
            addCriterion("right_group_identity is not null");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityEqualTo(String value) {
            addCriterion("right_group_identity =", value, "rightGroupIdentity");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityNotEqualTo(String value) {
            addCriterion("right_group_identity <>", value, "rightGroupIdentity");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityGreaterThan(String value) {
            addCriterion("right_group_identity >", value, "rightGroupIdentity");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityGreaterThanOrEqualTo(String value) {
            addCriterion("right_group_identity >=", value, "rightGroupIdentity");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityLessThan(String value) {
            addCriterion("right_group_identity <", value, "rightGroupIdentity");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityLessThanOrEqualTo(String value) {
            addCriterion("right_group_identity <=", value, "rightGroupIdentity");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityLike(String value) {
            addCriterion("right_group_identity like", value, "rightGroupIdentity");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityNotLike(String value) {
            addCriterion("right_group_identity not like", value, "rightGroupIdentity");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityIn(List<String> values) {
            addCriterion("right_group_identity in", values, "rightGroupIdentity");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityNotIn(List<String> values) {
            addCriterion("right_group_identity not in", values, "rightGroupIdentity");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityBetween(String value1, String value2) {
            addCriterion("right_group_identity between", value1, value2, "rightGroupIdentity");
            return (Criteria) this;
        }

        public Criteria andRightGroupIdentityNotBetween(String value1, String value2) {
            addCriterion("right_group_identity not between", value1, value2, "rightGroupIdentity");
            return (Criteria) this;
        }

        public Criteria andRightNameIsNull() {
            addCriterion("right_name is null");
            return (Criteria) this;
        }

        public Criteria andRightNameIsNotNull() {
            addCriterion("right_name is not null");
            return (Criteria) this;
        }

        public Criteria andRightNameEqualTo(String value) {
            addCriterion("right_name =", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameNotEqualTo(String value) {
            addCriterion("right_name <>", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameGreaterThan(String value) {
            addCriterion("right_name >", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameGreaterThanOrEqualTo(String value) {
            addCriterion("right_name >=", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameLessThan(String value) {
            addCriterion("right_name <", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameLessThanOrEqualTo(String value) {
            addCriterion("right_name <=", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameLike(String value) {
            addCriterion("right_name like", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameNotLike(String value) {
            addCriterion("right_name not like", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameIn(List<String> values) {
            addCriterion("right_name in", values, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameNotIn(List<String> values) {
            addCriterion("right_name not in", values, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameBetween(String value1, String value2) {
            addCriterion("right_name between", value1, value2, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameNotBetween(String value1, String value2) {
            addCriterion("right_name not between", value1, value2, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightDataIsNull() {
            addCriterion("right_data is null");
            return (Criteria) this;
        }

        public Criteria andRightDataIsNotNull() {
            addCriterion("right_data is not null");
            return (Criteria) this;
        }

        public Criteria andRightDataEqualTo(String value) {
            addCriterion("right_data =", value, "rightData");
            return (Criteria) this;
        }

        public Criteria andRightDataNotEqualTo(String value) {
            addCriterion("right_data <>", value, "rightData");
            return (Criteria) this;
        }

        public Criteria andRightDataGreaterThan(String value) {
            addCriterion("right_data >", value, "rightData");
            return (Criteria) this;
        }

        public Criteria andRightDataGreaterThanOrEqualTo(String value) {
            addCriterion("right_data >=", value, "rightData");
            return (Criteria) this;
        }

        public Criteria andRightDataLessThan(String value) {
            addCriterion("right_data <", value, "rightData");
            return (Criteria) this;
        }

        public Criteria andRightDataLessThanOrEqualTo(String value) {
            addCriterion("right_data <=", value, "rightData");
            return (Criteria) this;
        }

        public Criteria andRightDataLike(String value) {
            addCriterion("right_data like", value, "rightData");
            return (Criteria) this;
        }

        public Criteria andRightDataNotLike(String value) {
            addCriterion("right_data not like", value, "rightData");
            return (Criteria) this;
        }

        public Criteria andRightDataIn(List<String> values) {
            addCriterion("right_data in", values, "rightData");
            return (Criteria) this;
        }

        public Criteria andRightDataNotIn(List<String> values) {
            addCriterion("right_data not in", values, "rightData");
            return (Criteria) this;
        }

        public Criteria andRightDataBetween(String value1, String value2) {
            addCriterion("right_data between", value1, value2, "rightData");
            return (Criteria) this;
        }

        public Criteria andRightDataNotBetween(String value1, String value2) {
            addCriterion("right_data not between", value1, value2, "rightData");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionIsNull() {
            addCriterion("right_description is null");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionIsNotNull() {
            addCriterion("right_description is not null");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionEqualTo(String value) {
            addCriterion("right_description =", value, "rightDescription");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionNotEqualTo(String value) {
            addCriterion("right_description <>", value, "rightDescription");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionGreaterThan(String value) {
            addCriterion("right_description >", value, "rightDescription");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("right_description >=", value, "rightDescription");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionLessThan(String value) {
            addCriterion("right_description <", value, "rightDescription");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionLessThanOrEqualTo(String value) {
            addCriterion("right_description <=", value, "rightDescription");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionLike(String value) {
            addCriterion("right_description like", value, "rightDescription");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionNotLike(String value) {
            addCriterion("right_description not like", value, "rightDescription");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionIn(List<String> values) {
            addCriterion("right_description in", values, "rightDescription");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionNotIn(List<String> values) {
            addCriterion("right_description not in", values, "rightDescription");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionBetween(String value1, String value2) {
            addCriterion("right_description between", value1, value2, "rightDescription");
            return (Criteria) this;
        }

        public Criteria andRightDescriptionNotBetween(String value1, String value2) {
            addCriterion("right_description not between", value1, value2, "rightDescription");
            return (Criteria) this;
        }

        public Criteria andRightIdentityIsNull() {
            addCriterion("right_identity is null");
            return (Criteria) this;
        }

        public Criteria andRightIdentityIsNotNull() {
            addCriterion("right_identity is not null");
            return (Criteria) this;
        }

        public Criteria andRightIdentityEqualTo(String value) {
            addCriterion("right_identity =", value, "rightIdentity");
            return (Criteria) this;
        }

        public Criteria andRightIdentityNotEqualTo(String value) {
            addCriterion("right_identity <>", value, "rightIdentity");
            return (Criteria) this;
        }

        public Criteria andRightIdentityGreaterThan(String value) {
            addCriterion("right_identity >", value, "rightIdentity");
            return (Criteria) this;
        }

        public Criteria andRightIdentityGreaterThanOrEqualTo(String value) {
            addCriterion("right_identity >=", value, "rightIdentity");
            return (Criteria) this;
        }

        public Criteria andRightIdentityLessThan(String value) {
            addCriterion("right_identity <", value, "rightIdentity");
            return (Criteria) this;
        }

        public Criteria andRightIdentityLessThanOrEqualTo(String value) {
            addCriterion("right_identity <=", value, "rightIdentity");
            return (Criteria) this;
        }

        public Criteria andRightIdentityLike(String value) {
            addCriterion("right_identity like", value, "rightIdentity");
            return (Criteria) this;
        }

        public Criteria andRightIdentityNotLike(String value) {
            addCriterion("right_identity not like", value, "rightIdentity");
            return (Criteria) this;
        }

        public Criteria andRightIdentityIn(List<String> values) {
            addCriterion("right_identity in", values, "rightIdentity");
            return (Criteria) this;
        }

        public Criteria andRightIdentityNotIn(List<String> values) {
            addCriterion("right_identity not in", values, "rightIdentity");
            return (Criteria) this;
        }

        public Criteria andRightIdentityBetween(String value1, String value2) {
            addCriterion("right_identity between", value1, value2, "rightIdentity");
            return (Criteria) this;
        }

        public Criteria andRightIdentityNotBetween(String value1, String value2) {
            addCriterion("right_identity not between", value1, value2, "rightIdentity");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}