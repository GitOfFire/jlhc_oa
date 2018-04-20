package com.jlhc.base_com.dto.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CompanyExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public CompanyExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andComIdIsNull() {
            addCriterion("com_id is null");
            return (Criteria) this;
        }

        public Criteria andComIdIsNotNull() {
            addCriterion("com_id is not null");
            return (Criteria) this;
        }

        public Criteria andComIdEqualTo(String value) {
            addCriterion("com_id =", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdNotEqualTo(String value) {
            addCriterion("com_id <>", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdGreaterThan(String value) {
            addCriterion("com_id >", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdGreaterThanOrEqualTo(String value) {
            addCriterion("com_id >=", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdLessThan(String value) {
            addCriterion("com_id <", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdLessThanOrEqualTo(String value) {
            addCriterion("com_id <=", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdLike(String value) {
            addCriterion("com_id like", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdNotLike(String value) {
            addCriterion("com_id not like", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdIn(List<String> values) {
            addCriterion("com_id in", values, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdNotIn(List<String> values) {
            addCriterion("com_id not in", values, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdBetween(String value1, String value2) {
            addCriterion("com_id between", value1, value2, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdNotBetween(String value1, String value2) {
            addCriterion("com_id not between", value1, value2, "comId");
            return (Criteria) this;
        }

        public Criteria andComUnicodeIsNull() {
            addCriterion("com_unicode is null");
            return (Criteria) this;
        }

        public Criteria andComUnicodeIsNotNull() {
            addCriterion("com_unicode is not null");
            return (Criteria) this;
        }

        public Criteria andComUnicodeEqualTo(String value) {
            addCriterion("com_unicode =", value, "comUnicode");
            return (Criteria) this;
        }

        public Criteria andComUnicodeNotEqualTo(String value) {
            addCriterion("com_unicode <>", value, "comUnicode");
            return (Criteria) this;
        }

        public Criteria andComUnicodeGreaterThan(String value) {
            addCriterion("com_unicode >", value, "comUnicode");
            return (Criteria) this;
        }

        public Criteria andComUnicodeGreaterThanOrEqualTo(String value) {
            addCriterion("com_unicode >=", value, "comUnicode");
            return (Criteria) this;
        }

        public Criteria andComUnicodeLessThan(String value) {
            addCriterion("com_unicode <", value, "comUnicode");
            return (Criteria) this;
        }

        public Criteria andComUnicodeLessThanOrEqualTo(String value) {
            addCriterion("com_unicode <=", value, "comUnicode");
            return (Criteria) this;
        }

        public Criteria andComUnicodeLike(String value) {
            addCriterion("com_unicode like", value, "comUnicode");
            return (Criteria) this;
        }

        public Criteria andComUnicodeNotLike(String value) {
            addCriterion("com_unicode not like", value, "comUnicode");
            return (Criteria) this;
        }

        public Criteria andComUnicodeIn(List<String> values) {
            addCriterion("com_unicode in", values, "comUnicode");
            return (Criteria) this;
        }

        public Criteria andComUnicodeNotIn(List<String> values) {
            addCriterion("com_unicode not in", values, "comUnicode");
            return (Criteria) this;
        }

        public Criteria andComUnicodeBetween(String value1, String value2) {
            addCriterion("com_unicode between", value1, value2, "comUnicode");
            return (Criteria) this;
        }

        public Criteria andComUnicodeNotBetween(String value1, String value2) {
            addCriterion("com_unicode not between", value1, value2, "comUnicode");
            return (Criteria) this;
        }

        public Criteria andComNameIsNull() {
            addCriterion("com_name is null");
            return (Criteria) this;
        }

        public Criteria andComNameIsNotNull() {
            addCriterion("com_name is not null");
            return (Criteria) this;
        }

        public Criteria andComNameEqualTo(String value) {
            addCriterion("com_name =", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameNotEqualTo(String value) {
            addCriterion("com_name <>", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameGreaterThan(String value) {
            addCriterion("com_name >", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameGreaterThanOrEqualTo(String value) {
            addCriterion("com_name >=", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameLessThan(String value) {
            addCriterion("com_name <", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameLessThanOrEqualTo(String value) {
            addCriterion("com_name <=", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameLike(String value) {
            addCriterion("com_name like", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameNotLike(String value) {
            addCriterion("com_name not like", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameIn(List<String> values) {
            addCriterion("com_name in", values, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameNotIn(List<String> values) {
            addCriterion("com_name not in", values, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameBetween(String value1, String value2) {
            addCriterion("com_name between", value1, value2, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameNotBetween(String value1, String value2) {
            addCriterion("com_name not between", value1, value2, "comName");
            return (Criteria) this;
        }

        public Criteria andComTypeIsNull() {
            addCriterion("com_type is null");
            return (Criteria) this;
        }

        public Criteria andComTypeIsNotNull() {
            addCriterion("com_type is not null");
            return (Criteria) this;
        }

        public Criteria andComTypeEqualTo(Integer value) {
            addCriterion("com_type =", value, "comType");
            return (Criteria) this;
        }

        public Criteria andComTypeNotEqualTo(Integer value) {
            addCriterion("com_type <>", value, "comType");
            return (Criteria) this;
        }

        public Criteria andComTypeGreaterThan(Integer value) {
            addCriterion("com_type >", value, "comType");
            return (Criteria) this;
        }

        public Criteria andComTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("com_type >=", value, "comType");
            return (Criteria) this;
        }

        public Criteria andComTypeLessThan(Integer value) {
            addCriterion("com_type <", value, "comType");
            return (Criteria) this;
        }

        public Criteria andComTypeLessThanOrEqualTo(Integer value) {
            addCriterion("com_type <=", value, "comType");
            return (Criteria) this;
        }

        public Criteria andComTypeIn(List<Integer> values) {
            addCriterion("com_type in", values, "comType");
            return (Criteria) this;
        }

        public Criteria andComTypeNotIn(List<Integer> values) {
            addCriterion("com_type not in", values, "comType");
            return (Criteria) this;
        }

        public Criteria andComTypeBetween(Integer value1, Integer value2) {
            addCriterion("com_type between", value1, value2, "comType");
            return (Criteria) this;
        }

        public Criteria andComTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("com_type not between", value1, value2, "comType");
            return (Criteria) this;
        }

        public Criteria andComAddressIsNull() {
            addCriterion("com_address is null");
            return (Criteria) this;
        }

        public Criteria andComAddressIsNotNull() {
            addCriterion("com_address is not null");
            return (Criteria) this;
        }

        public Criteria andComAddressEqualTo(String value) {
            addCriterion("com_address =", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressNotEqualTo(String value) {
            addCriterion("com_address <>", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressGreaterThan(String value) {
            addCriterion("com_address >", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressGreaterThanOrEqualTo(String value) {
            addCriterion("com_address >=", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressLessThan(String value) {
            addCriterion("com_address <", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressLessThanOrEqualTo(String value) {
            addCriterion("com_address <=", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressLike(String value) {
            addCriterion("com_address like", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressNotLike(String value) {
            addCriterion("com_address not like", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressIn(List<String> values) {
            addCriterion("com_address in", values, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressNotIn(List<String> values) {
            addCriterion("com_address not in", values, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressBetween(String value1, String value2) {
            addCriterion("com_address between", value1, value2, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressNotBetween(String value1, String value2) {
            addCriterion("com_address not between", value1, value2, "comAddress");
            return (Criteria) this;
        }

        public Criteria andCusNameIsNull() {
            addCriterion("cus_name is null");
            return (Criteria) this;
        }

        public Criteria andCusNameIsNotNull() {
            addCriterion("cus_name is not null");
            return (Criteria) this;
        }

        public Criteria andCusNameEqualTo(String value) {
            addCriterion("cus_name =", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameNotEqualTo(String value) {
            addCriterion("cus_name <>", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameGreaterThan(String value) {
            addCriterion("cus_name >", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameGreaterThanOrEqualTo(String value) {
            addCriterion("cus_name >=", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameLessThan(String value) {
            addCriterion("cus_name <", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameLessThanOrEqualTo(String value) {
            addCriterion("cus_name <=", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameLike(String value) {
            addCriterion("cus_name like", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameNotLike(String value) {
            addCriterion("cus_name not like", value, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameIn(List<String> values) {
            addCriterion("cus_name in", values, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameNotIn(List<String> values) {
            addCriterion("cus_name not in", values, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameBetween(String value1, String value2) {
            addCriterion("cus_name between", value1, value2, "cusName");
            return (Criteria) this;
        }

        public Criteria andCusNameNotBetween(String value1, String value2) {
            addCriterion("cus_name not between", value1, value2, "cusName");
            return (Criteria) this;
        }

        public Criteria andComCapitalIsNull() {
            addCriterion("com_capital is null");
            return (Criteria) this;
        }

        public Criteria andComCapitalIsNotNull() {
            addCriterion("com_capital is not null");
            return (Criteria) this;
        }

        public Criteria andComCapitalEqualTo(Long value) {
            addCriterion("com_capital =", value, "comCapital");
            return (Criteria) this;
        }

        public Criteria andComCapitalNotEqualTo(Long value) {
            addCriterion("com_capital <>", value, "comCapital");
            return (Criteria) this;
        }

        public Criteria andComCapitalGreaterThan(Long value) {
            addCriterion("com_capital >", value, "comCapital");
            return (Criteria) this;
        }

        public Criteria andComCapitalGreaterThanOrEqualTo(Long value) {
            addCriterion("com_capital >=", value, "comCapital");
            return (Criteria) this;
        }

        public Criteria andComCapitalLessThan(Long value) {
            addCriterion("com_capital <", value, "comCapital");
            return (Criteria) this;
        }

        public Criteria andComCapitalLessThanOrEqualTo(Long value) {
            addCriterion("com_capital <=", value, "comCapital");
            return (Criteria) this;
        }

        public Criteria andComCapitalIn(List<Long> values) {
            addCriterion("com_capital in", values, "comCapital");
            return (Criteria) this;
        }

        public Criteria andComCapitalNotIn(List<Long> values) {
            addCriterion("com_capital not in", values, "comCapital");
            return (Criteria) this;
        }

        public Criteria andComCapitalBetween(Long value1, Long value2) {
            addCriterion("com_capital between", value1, value2, "comCapital");
            return (Criteria) this;
        }

        public Criteria andComCapitalNotBetween(Long value1, Long value2) {
            addCriterion("com_capital not between", value1, value2, "comCapital");
            return (Criteria) this;
        }

        public Criteria andComFoundTimeIsNull() {
            addCriterion("com_found_time is null");
            return (Criteria) this;
        }

        public Criteria andComFoundTimeIsNotNull() {
            addCriterion("com_found_time is not null");
            return (Criteria) this;
        }

        public Criteria andComFoundTimeEqualTo(Date value) {
            addCriterionForJDBCDate("com_found_time =", value, "comFoundTime");
            return (Criteria) this;
        }

        public Criteria andComFoundTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("com_found_time <>", value, "comFoundTime");
            return (Criteria) this;
        }

        public Criteria andComFoundTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("com_found_time >", value, "comFoundTime");
            return (Criteria) this;
        }

        public Criteria andComFoundTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("com_found_time >=", value, "comFoundTime");
            return (Criteria) this;
        }

        public Criteria andComFoundTimeLessThan(Date value) {
            addCriterionForJDBCDate("com_found_time <", value, "comFoundTime");
            return (Criteria) this;
        }

        public Criteria andComFoundTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("com_found_time <=", value, "comFoundTime");
            return (Criteria) this;
        }

        public Criteria andComFoundTimeIn(List<Date> values) {
            addCriterionForJDBCDate("com_found_time in", values, "comFoundTime");
            return (Criteria) this;
        }

        public Criteria andComFoundTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("com_found_time not in", values, "comFoundTime");
            return (Criteria) this;
        }

        public Criteria andComFoundTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("com_found_time between", value1, value2, "comFoundTime");
            return (Criteria) this;
        }

        public Criteria andComFoundTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("com_found_time not between", value1, value2, "comFoundTime");
            return (Criteria) this;
        }

        public Criteria andComBusinessTermIsNull() {
            addCriterion("com_business_term is null");
            return (Criteria) this;
        }

        public Criteria andComBusinessTermIsNotNull() {
            addCriterion("com_business_term is not null");
            return (Criteria) this;
        }

        public Criteria andComBusinessTermEqualTo(Long value) {
            addCriterion("com_business_term =", value, "comBusinessTerm");
            return (Criteria) this;
        }

        public Criteria andComBusinessTermNotEqualTo(Long value) {
            addCriterion("com_business_term <>", value, "comBusinessTerm");
            return (Criteria) this;
        }

        public Criteria andComBusinessTermGreaterThan(Long value) {
            addCriterion("com_business_term >", value, "comBusinessTerm");
            return (Criteria) this;
        }

        public Criteria andComBusinessTermGreaterThanOrEqualTo(Long value) {
            addCriterion("com_business_term >=", value, "comBusinessTerm");
            return (Criteria) this;
        }

        public Criteria andComBusinessTermLessThan(Long value) {
            addCriterion("com_business_term <", value, "comBusinessTerm");
            return (Criteria) this;
        }

        public Criteria andComBusinessTermLessThanOrEqualTo(Long value) {
            addCriterion("com_business_term <=", value, "comBusinessTerm");
            return (Criteria) this;
        }

        public Criteria andComBusinessTermIn(List<Long> values) {
            addCriterion("com_business_term in", values, "comBusinessTerm");
            return (Criteria) this;
        }

        public Criteria andComBusinessTermNotIn(List<Long> values) {
            addCriterion("com_business_term not in", values, "comBusinessTerm");
            return (Criteria) this;
        }

        public Criteria andComBusinessTermBetween(Long value1, Long value2) {
            addCriterion("com_business_term between", value1, value2, "comBusinessTerm");
            return (Criteria) this;
        }

        public Criteria andComBusinessTermNotBetween(Long value1, Long value2) {
            addCriterion("com_business_term not between", value1, value2, "comBusinessTerm");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeIsNull() {
            addCriterion("com_bussiness_scope is null");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeIsNotNull() {
            addCriterion("com_bussiness_scope is not null");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeEqualTo(String value) {
            addCriterion("com_bussiness_scope =", value, "comBussinessScope");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeNotEqualTo(String value) {
            addCriterion("com_bussiness_scope <>", value, "comBussinessScope");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeGreaterThan(String value) {
            addCriterion("com_bussiness_scope >", value, "comBussinessScope");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeGreaterThanOrEqualTo(String value) {
            addCriterion("com_bussiness_scope >=", value, "comBussinessScope");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeLessThan(String value) {
            addCriterion("com_bussiness_scope <", value, "comBussinessScope");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeLessThanOrEqualTo(String value) {
            addCriterion("com_bussiness_scope <=", value, "comBussinessScope");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeLike(String value) {
            addCriterion("com_bussiness_scope like", value, "comBussinessScope");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeNotLike(String value) {
            addCriterion("com_bussiness_scope not like", value, "comBussinessScope");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeIn(List<String> values) {
            addCriterion("com_bussiness_scope in", values, "comBussinessScope");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeNotIn(List<String> values) {
            addCriterion("com_bussiness_scope not in", values, "comBussinessScope");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeBetween(String value1, String value2) {
            addCriterion("com_bussiness_scope between", value1, value2, "comBussinessScope");
            return (Criteria) this;
        }

        public Criteria andComBussinessScopeNotBetween(String value1, String value2) {
            addCriterion("com_bussiness_scope not between", value1, value2, "comBussinessScope");
            return (Criteria) this;
        }

        public Criteria andComDescriptionIsNull() {
            addCriterion("com_description is null");
            return (Criteria) this;
        }

        public Criteria andComDescriptionIsNotNull() {
            addCriterion("com_description is not null");
            return (Criteria) this;
        }

        public Criteria andComDescriptionEqualTo(String value) {
            addCriterion("com_description =", value, "comDescription");
            return (Criteria) this;
        }

        public Criteria andComDescriptionNotEqualTo(String value) {
            addCriterion("com_description <>", value, "comDescription");
            return (Criteria) this;
        }

        public Criteria andComDescriptionGreaterThan(String value) {
            addCriterion("com_description >", value, "comDescription");
            return (Criteria) this;
        }

        public Criteria andComDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("com_description >=", value, "comDescription");
            return (Criteria) this;
        }

        public Criteria andComDescriptionLessThan(String value) {
            addCriterion("com_description <", value, "comDescription");
            return (Criteria) this;
        }

        public Criteria andComDescriptionLessThanOrEqualTo(String value) {
            addCriterion("com_description <=", value, "comDescription");
            return (Criteria) this;
        }

        public Criteria andComDescriptionLike(String value) {
            addCriterion("com_description like", value, "comDescription");
            return (Criteria) this;
        }

        public Criteria andComDescriptionNotLike(String value) {
            addCriterion("com_description not like", value, "comDescription");
            return (Criteria) this;
        }

        public Criteria andComDescriptionIn(List<String> values) {
            addCriterion("com_description in", values, "comDescription");
            return (Criteria) this;
        }

        public Criteria andComDescriptionNotIn(List<String> values) {
            addCriterion("com_description not in", values, "comDescription");
            return (Criteria) this;
        }

        public Criteria andComDescriptionBetween(String value1, String value2) {
            addCriterion("com_description between", value1, value2, "comDescription");
            return (Criteria) this;
        }

        public Criteria andComDescriptionNotBetween(String value1, String value2) {
            addCriterion("com_description not between", value1, value2, "comDescription");
            return (Criteria) this;
        }

        public Criteria andComStateTypeIsNull() {
            addCriterion("com_state_type is null");
            return (Criteria) this;
        }

        public Criteria andComStateTypeIsNotNull() {
            addCriterion("com_state_type is not null");
            return (Criteria) this;
        }

        public Criteria andComStateTypeEqualTo(Integer value) {
            addCriterion("com_state_type =", value, "comStateType");
            return (Criteria) this;
        }

        public Criteria andComStateTypeNotEqualTo(Integer value) {
            addCriterion("com_state_type <>", value, "comStateType");
            return (Criteria) this;
        }

        public Criteria andComStateTypeGreaterThan(Integer value) {
            addCriterion("com_state_type >", value, "comStateType");
            return (Criteria) this;
        }

        public Criteria andComStateTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("com_state_type >=", value, "comStateType");
            return (Criteria) this;
        }

        public Criteria andComStateTypeLessThan(Integer value) {
            addCriterion("com_state_type <", value, "comStateType");
            return (Criteria) this;
        }

        public Criteria andComStateTypeLessThanOrEqualTo(Integer value) {
            addCriterion("com_state_type <=", value, "comStateType");
            return (Criteria) this;
        }

        public Criteria andComStateTypeIn(List<Integer> values) {
            addCriterion("com_state_type in", values, "comStateType");
            return (Criteria) this;
        }

        public Criteria andComStateTypeNotIn(List<Integer> values) {
            addCriterion("com_state_type not in", values, "comStateType");
            return (Criteria) this;
        }

        public Criteria andComStateTypeBetween(Integer value1, Integer value2) {
            addCriterion("com_state_type between", value1, value2, "comStateType");
            return (Criteria) this;
        }

        public Criteria andComStateTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("com_state_type not between", value1, value2, "comStateType");
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