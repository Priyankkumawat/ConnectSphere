package com.social.ConnectSphere.Payload;

import lombok.Data;

import java.util.Date;
@Data
public class PostDTO {
    public String content;
    public Date addedDate;
    public String imageName;
}
