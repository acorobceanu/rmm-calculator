# rmm-calculator
Mortgage loan remaining maturity months term calculator.

Formula "Remaining Months to Maturity (RMM)" used can be found on official FreddieMac site http://www.freddiemac.com/mbs/docs/pc_algorithms.pdf for MBS algorithms.

RMM = - (FUNCTION LOG10 (1- (Issuance Investor Loan UPB*((Issuance Interest Rate/1200) / Loan Principal
& Interest at Origination Payment Amount)))) / FUNCTION LOG10 (1 + (Issuance Interest Rate/1200))
