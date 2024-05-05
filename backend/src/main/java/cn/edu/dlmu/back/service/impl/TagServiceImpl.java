package cn.edu.dlmu.back.service.impl;

import cn.edu.dlmu.back.model.domain.Tag;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.dlmu.back.service.TagService;
import cn.edu.dlmu.back.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author Silenceibtc
* @description 针对表【tag(标签表)】的数据库操作Service实现
* @createDate 2024-05-04 21:34:54
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




