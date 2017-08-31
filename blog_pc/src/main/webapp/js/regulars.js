//带逗号分隔的金额，或小数点左边任意位数的数字。小数点后只能输入两位数字.
var REG_EXP_AMOUNT = /^((\d{1,3}(,\d{3})*)|(\d+))(\.\d{2})?$/;
//带逗号分隔的金额
var REG_EXP_AMOUNT_FINANCIAL = /^((\d{1,3}(,\d{3})*))(\.\d{2})?$/;
//非负整数（正整数 + 0）
var REG_EXP_POSITIVE_INT = /^\d+$/;
//Email地址
var REG_EMAIL = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
//验证数字
var REG_NUM = /^[-+]?\d+(\.\d+)?$/;
//电话号码
var REG_PHONE_NUM = /^[+]?\d[\d\-]*\d$/;