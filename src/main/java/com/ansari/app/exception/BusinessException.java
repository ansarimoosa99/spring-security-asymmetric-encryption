package com.ansari.app.exception;

public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Object[] args;
    
    public BusinessException(final ErrorCode errorCode, final Object... args){ //3 dots mean - passing a table
            super(getFormatterMessage(errorCode, args));
            this.errorCode = errorCode;
            this.args = args;
    }


    //for safety as we have %s in error formatting, if no parameneter is passed then there is an issue-- also internationalization[making it standard], localization
    private static String getFormatterMessage(ErrorCode errorCode, Object[] args) {
        if(args != null && args.length > 0) {
            return String.format(errorCode.getDefaultMessage(), args);
        }
        return errorCode.getDefaultMessage();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
