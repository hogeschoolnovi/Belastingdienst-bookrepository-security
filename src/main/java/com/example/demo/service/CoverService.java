package com.example.demo.service;

import com.example.demo.dto.CoverDto;
import com.example.demo.model.Cover;
import org.springframework.stereotype.Service;

@Service
public class CoverService {

    public static CoverDto fromCover(Cover cover){
        CoverDto dto = new CoverDto(cover.getId(), cover.getBook(), cover.getColor(), cover.getMaterial());
        return dto;
    }

    public static Cover toCover(CoverDto dto){
        Cover cover = new Cover();
        cover.setId(dto.getId());
        cover.setBook(dto.getBook());
        cover.setColor(dto.getColor());
        cover.setMaterial(dto.getMaterial());
        return cover;
    }

}
