package com.zhang.blog.vo.converter;

import java.util.List;

/**
 * 类描述：父类转换器
 * 创建时间：2020/6/23 4:42 下午
 * 创建人：zhang
 */
public interface BaseConverter<D, E> {



    /**
     * DTO转Entity
     * @param dto /
     * @return /
     */
    E toEntity(D dto);

    /**
     * Entity转DTO
     * @param entity /
     * @return /
     */
    D toDto(E entity);

    /**
     * DTO集合转Entity集合
     * @param dtoList /
     * @return /
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Entity集合转DTO集合
     * @param entityList /
     * @return /
     */
    List <D> toDto(List<E> entityList);
}
