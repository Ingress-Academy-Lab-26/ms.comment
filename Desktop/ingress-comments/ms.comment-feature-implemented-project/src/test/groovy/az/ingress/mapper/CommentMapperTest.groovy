package az.ingress.mapper

import az.ingress.dao.entity.CommentEntity
import az.ingress.mapper.CommentMapper
import az.ingress.model.request.CreateOrUpdateCommentRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class CommentMapperTest extends Specification{
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    def  "buildCommentEntityTest"(){
        given:
        def request = random.nextObject(CreateOrUpdateCommentRequest)
        when:
        def entity = CommentMapper.COMMENT_MAPPER.buildCommentEntity(request)
        then:
        entity.userId == request.userId
        entity.productId == request.productId
        entity.content == request.content

    }
    def "buildPageableResponse"(){
        given:
        def response = random.nextObject(Page<CommentEntity>)
        when:
        def pageableResponse = CommentMapper.COMMENT_MAPPER.buildPageableResponse(response)
        then:
        response.content == pageableResponse.content
        response.totalElements == pageableResponse.totalElements
        response.totalPages == pageableResponse.totalPages
    }
    def "buildCommentResponse"(){
        given:
        def entity = random.nextObject(CommentEntity)
        when:
        def commentResponse = CommentMapper.COMMENT_MAPPER.buildCommentResponse(entity)
        then:
        entity.content == commentResponse.content
        entity.productId == commentResponse.productId
        entity.userId == commentResponse.userId
    }
    def "updateComment"(){
        given:
        def entity = random.nextObject(CommentEntity)
        def response = random.nextObject(CreateOrUpdateCommentRequest)
        when:
        CommentMapper.COMMENT_MAPPER.updateComment(entity,response)
        then:
        entity.userId == response.userId
        entity.productId == response.productId
        entity.content == response.content
    }





}
