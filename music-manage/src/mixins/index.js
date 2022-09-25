export const mixin = {
  methods: {
    // 提示信息
    notify (title, type) {
      this.$notify({
        title: title,
        type: type
      })
    },
    // 根据相对地址获取绝对地址
    getUrl (url) {
      return `${this.$store.state.HOST}${url}`
    },
    // 根据性别数获取性别
    changeSex (value) {
      if (value === 0) {
        return '女'
      }
      if (value === 1) {
        return '男'
      }
      if (value === 2) {
        return '组合'
      }
      if (value === 3) {
        return '不明'
      }
      return value
    },
    // 获取生日
    attachBirth (val) {
      return String(val).substring(0, 10)
    },
    // 上传之前校验
    beforeAvatorUpload (file) {
      const isJPG = (file.type === 'image/jpg') || (file.type === 'image/png')
      if (isJPG) {
        this.$message.error('上传图片只能是jpg或者png格式')
        return false
      }
      const isLt2M = (file.size / 1024 / 1024) < 2
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过2MB')
        return false
      }
      return true
    },
    // 上传图片成功之后
    handleAvatorSuccess (res, file) {
      let _this = this
      if (res.code === 1) {
        _this.getData()
        _this.$notify({
          title: '上传成功',
          type: 'success'
        })
      } else {
        _this.getData()
        _this.$notify({
          title: '上传失败',
          type: 'error'
        })
      }
    },
    // 弹出删除窗口
    handleDelete (id) {
      this.idx = id
      this.deleVisible = true
    },
    // 把已经选择的id赋值给multipleSelection
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    // 批量删除的
    delAll () {
      for (let item of this.multipleSelection) {
        this.handleDelete(item.id)
        this.deleteSinger()
      }
      this.multipleSelection = []
      this.getData()
    }
  }
}
