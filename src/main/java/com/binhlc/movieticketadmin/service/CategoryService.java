package com.binhlc.movieticketadmin.service;

import com.binhlc.movieticketadmin.domain.entity.CategoryEntity;
import com.binhlc.movieticketadmin.domain.repo.CategoryRepo;
import com.binhlc.movieticketadmin.domain.reponse.Result;
import com.binhlc.movieticketadmin.domain.request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends BaseService {
    @Autowired
    private CategoryRepo mCateRepo;

    public int addCategory(CategoryEntity categoryEntity) {
        try{
            mCateRepo.save(categoryEntity);
            return  1;
        }catch (Exception e){
            System.err.println("[Category Service] Error: " + e.getMessage());
            return 0;
        }
    }

    public Result add(CategoryRequest request){
        CategoryEntity entity = new CategoryEntity();
        entity.setName(request.getName());
        entity.setCreateBy(1);
        entity.setUpdateBy(1);
        entity.setTimeCreate(currrentTime());
        entity.setTimeUpdate(currrentTime());
        return addCategory(entity) == 1 ? Result.success() : Result.fail();
    }


    public List<CategoryEntity> getListCategory(int start, int limit) {
        return null;
    }
}
