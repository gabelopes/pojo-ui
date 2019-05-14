window.Shell = (function() {
  const SHELL_ID = "shell";
  const TAB_BUTTON_CLASS = "tab-button";
  const TAB_CONTENT_CLASS = "tab-content";
  const TAB_PREFIX = "tab-";
  const CONTENT_PREFIX = "content-";
  const SELECTED_CLASS = "selected";
  const HIDDEN_CLASS = "hidden";

  function getContentId(tabId) {
    return tabId.slice(TAB_PREFIX.length);
  }

  return class Shell {
    constructor() {
      this.shell = $(`#${SHELL_ID}`);
      this.initialize();
    }

    initialize() {
      const tabButtons = $(`.${TAB_BUTTON_CLASS}`);

      tabButtons.click(this.handleTabClick.bind(this));
      tabButtons.first().click();
    }
  
    switchToTab(tabId) {
      const contentId = getContentId(tabId);
  
      $(`.${TAB_BUTTON_CLASS}`).removeClass(SELECTED_CLASS);
      $(`.${TAB_CONTENT_CLASS}`).addClass(HIDDEN_CLASS);

      $(`#${tabId}`).addClass(SELECTED_CLASS);
      $(`#${CONTENT_PREFIX}${contentId}`).removeClass(HIDDEN_CLASS);
    }

    handleTabClick(clickEvent) {
      const clickedTab = clickEvent.target;
      const tabId = clickedTab.id;

      this.switchToTab(tabId);
    }
  }
})();
