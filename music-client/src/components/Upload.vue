<template>
    <div class="upload">
      <P class="title">修改头像</P>
      <hr/>
      <div class="section">
        <el-upload drag :action="uploadUrl()" :show-file-list="false" :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <i class="el-icon-upload"></i>
        <div>
            将文件拖拽到此处，或者<span style="color:blue">修改头像</span>
        </div>
        <div slot="tip">只能上传jpg/png文件，且不能超过10MB</div>
        </el-upload>
      </div>
    </div>
</template>
<script>
import { mapGetters } from 'vuex'
import { mixin } from '../mixins'

export default {
  name: 'upload',
  mixins: [mixin],
  computed: {
    ...mapGetters([
      'userId'
    ])
  },
  methods: {
    // 上传地址
    uploadUrl () {
      return `${this.$store.state.configure.HOST}/consumer/updateConsumerPic?id=${this.userId}`
    },
    // 上传成功
    handleAvatarSuccess (res, file) {
      if (res.code === 1) {
        this.$store.commit('setAvatar', res.avatar)
        this.notify('修改成功', 'success')
      } else {
        this.notify('修改失败', 'error')
      }
    },
    // 上传校验
    beforeAvatarUpload (file) {
      const isJpg = file.type === 'image/jpeg'
      const isLt10M = file.size / 1024 / 1024 < 10
      if (!isJpg) {
        this.notify('上传头像只能收jpg格式', 'error')
        return false
      }
      if (!isLt10M) {
        this.notify('上头像图片不能大于10M', 'error')
        return false
      }
      return true
    }
  }

}
</script>
<style lang="scss" scoped>
@import '../assets/css/upload.scss'
</style>
