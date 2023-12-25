package com.golosovalka.payload;


public class ApiResponse {
    private Boolean success;    //поле для хранения успешности операции
    private String message; //поле для хранения сообщения об операции
    //@ApiResponse - конструктор класса, который принимает два параметра: успешность операции и сообщение об операции
    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    //@getSuccess - геттер для поля success, который возвращает значение успешности операции
    public Boolean getSuccess() {
        return success;
    }
    //@setSuccess - сеттер для поля success, который устанавливает значение успешности операции
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    //@getMessage - геттер для поля message, который возвращает сообщение об операции
    public String getMessage() {
        return message;
    }
    //@setMessage - сеттер для поля message, который устанавливает сообщение об операции
    public void setMessage(String message) {
        this.message = message;
    }
}
